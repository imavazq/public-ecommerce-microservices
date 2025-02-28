package com.imavazq.ecommerce.service.impl;

import com.imavazq.ecommerce.client.CustomerClient;
import com.imavazq.ecommerce.client.PaymentClient;
import com.imavazq.ecommerce.domain.dto.*;
import com.imavazq.ecommerce.domain.entity.Order;
import com.imavazq.ecommerce.exception.BusinessException;
import com.imavazq.ecommerce.kafka.OrderConfirmation;
import com.imavazq.ecommerce.mapper.OrderMapper;
import com.imavazq.ecommerce.kafka.OrderProducer;
import com.imavazq.ecommerce.client.ProductClient;
import com.imavazq.ecommerce.repository.OrderRepository;
import com.imavazq.ecommerce.service.IOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineServiceImpl orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Override
    public Integer createOrder(OrderRequestDTO orderRequestDTO) {
        //1-validamos existencia Customer --> (OpenFeign) usamos endpoint de customer-microservice
        CustomerResponseDTO customer = this.customerClient.findCustomerById(orderRequestDTO.customerId())
                .orElseThrow(() -> new BusinessException("Can not create order: No Customer exists with the provided id"));

        //2-comprar el producto --> (RestTemplate) usamos endpoint de product-microservice para que se encargue de actualizar y toda la logica al comprar un product
        List<PurchasedProductResponseDTO> purchasedProducts = this.productClient.purchaseProducts(orderRequestDTO.products());

        //3-persistimos la order
        Order order = this.orderRepository.save(orderMapper.toOrder(orderRequestDTO));

        //4-persistimos order lines (productos y su cantidad)
        for(PurchasedProductRequestDTO purchasedProduct : orderRequestDTO.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequestDTO(
                            null, //id orderLine que se asigne auto
                            order.getId(),
                            purchasedProduct.productId(),
                            purchasedProduct.quantity()
                    )
            ); //no nos quedamos con el id retornado por saveOrderLine en este caso porque no lo usamos
        }

        //5-empezamos proceso de pago (payment)
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(
                orderRequestDTO.amount(),
                orderRequestDTO.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        //6-enviamos la order confirmation a notification-microservice (Kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequestDTO.reference(),
                        orderRequestDTO.amount(),
                        orderRequestDTO.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO findById(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("No order found with the provided id: " + id));
    }
}
