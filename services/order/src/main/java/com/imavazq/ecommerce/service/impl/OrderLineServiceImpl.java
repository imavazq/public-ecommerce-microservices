package com.imavazq.ecommerce.service.impl;

import com.imavazq.ecommerce.domain.dto.OrderLineRequestDTO;
import com.imavazq.ecommerce.domain.dto.OrderLineResponseDTO;
import com.imavazq.ecommerce.domain.entity.OrderLine;
import com.imavazq.ecommerce.mapper.OrderLineMapper;
import com.imavazq.ecommerce.repository.OrderLineRepository;
import com.imavazq.ecommerce.service.IOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements IOrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    @Override
    public Integer saveOrderLine(OrderLineRequestDTO orderLineRequestDTO) {
        OrderLine orderLine = orderLineMapper.toOrderLine(orderLineRequestDTO);

        return orderLineRepository.save(orderLine).getId(); //devuelvo id
    }

    @Override
    public List<OrderLineResponseDTO> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream()
                .map(orderLineMapper::toOrderLineResponseDTO)
                .collect(Collectors.toList());
    }
}
