package com.imavazq.ecommerce.mapper;

import com.imavazq.ecommerce.domain.entity.Order;
import com.imavazq.ecommerce.domain.dto.OrderRequestDTO;
import com.imavazq.ecommerce.domain.dto.OrderResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequestDTO orderRequestDTO) {
        return Order.builder()
                .id(orderRequestDTO.id())
                .reference(orderRequestDTO.reference())
                .totalAmount(orderRequestDTO.amount())
                .paymentMethod(orderRequestDTO.paymentMethod())
                .customerId(orderRequestDTO.customerId())
                .build();
    }

    public OrderResponseDTO toOrderResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
