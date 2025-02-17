package com.imavazq.ecommerce.mapper;

import com.imavazq.ecommerce.domain.entity.Customer;
import com.imavazq.ecommerce.domain.dto.CustomerRequestDTO;
import com.imavazq.ecommerce.domain.dto.CustomerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequestDTO customerRequestDTO) {
        if(customerRequestDTO == null)
            return null;

        return Customer.builder()
                .id(customerRequestDTO.id())
                .firstName(customerRequestDTO.firstName())
                .lastName(customerRequestDTO.lastName())
                .email(customerRequestDTO.email())
                .address(customerRequestDTO.address())
                .build();
    }

    //Convertimos de Customer a CustomerResponseDTO (record)
    public CustomerResponseDTO toCustomerResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
