package com.imavazq.ecommerce.service;

import com.imavazq.ecommerce.domain.dto.OrderRequestDTO;
import com.imavazq.ecommerce.domain.dto.OrderResponseDTO;

import java.util.List;

public interface IOrderService {
    Integer createOrder(OrderRequestDTO orderRequestDTO);

    List<OrderResponseDTO> findAll();

    OrderResponseDTO findById(Integer id);
}
