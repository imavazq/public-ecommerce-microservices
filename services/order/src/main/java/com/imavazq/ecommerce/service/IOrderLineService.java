package com.imavazq.ecommerce.service;

import com.imavazq.ecommerce.domain.dto.OrderLineRequestDTO;
import com.imavazq.ecommerce.domain.dto.OrderLineResponseDTO;

import java.util.List;

public interface IOrderLineService {
    Integer saveOrderLine(OrderLineRequestDTO orderLineRequestDTO);

    List<OrderLineResponseDTO> findAllByOrderId(Integer orderId);
}
