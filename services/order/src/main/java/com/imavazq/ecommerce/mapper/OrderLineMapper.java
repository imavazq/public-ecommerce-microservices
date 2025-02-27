package com.imavazq.ecommerce.mapper;

import com.imavazq.ecommerce.domain.entity.Order;
import com.imavazq.ecommerce.domain.entity.OrderLine;
import com.imavazq.ecommerce.domain.dto.OrderLineRequestDTO;
import com.imavazq.ecommerce.domain.dto.OrderLineResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequestDTO orderLineRequestDTO) {
        return OrderLine.builder()
                .id(orderLineRequestDTO.id())
                .order(
                        Order.builder()
                                .id(orderLineRequestDTO.orderId())
                                .build()
                )
                .productId(orderLineRequestDTO.productId())
                .quantity(orderLineRequestDTO.quantity())
                .build();
    }

    public OrderLineResponseDTO toOrderLineResponseDTO(OrderLine orderLine) {
        return new OrderLineResponseDTO(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
