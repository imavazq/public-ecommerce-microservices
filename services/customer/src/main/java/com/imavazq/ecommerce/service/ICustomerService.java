package com.imavazq.ecommerce.service;

import com.imavazq.ecommerce.domain.dto.CustomerRequestDTO;
import com.imavazq.ecommerce.domain.dto.CustomerResponseDTO;

import java.util.List;

public interface ICustomerService {
    String createCustomer(CustomerRequestDTO customerRequestDTO);

    void updateCustomer(CustomerRequestDTO customerRequestDTO);

    List<CustomerResponseDTO> findAllCustomers();

    Boolean existsById(String id);

    CustomerResponseDTO findById(String id);

    void deleteCustomer(String id);
}
