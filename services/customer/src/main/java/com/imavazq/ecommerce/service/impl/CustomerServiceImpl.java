package com.imavazq.ecommerce.service.impl;

import com.imavazq.ecommerce.mapper.CustomerMapper;
import com.imavazq.ecommerce.repository.CustomerRepository;
import com.imavazq.ecommerce.domain.dto.CustomerRequestDTO;
import com.imavazq.ecommerce.domain.dto.CustomerResponseDTO;
import com.imavazq.ecommerce.exception.CustomerNotFoundException;
import com.imavazq.ecommerce.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public String createCustomer(CustomerRequestDTO customerRequestDTO) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequestDTO));//var permite inferir tipos (detecte automáticamente tipo)
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequestDTO customerRequestDTO) {
        customerRepository.findById(customerRequestDTO.id()).map(existingCustomer -> {//Si encuentra existingCustomer en bd
            //Si nuevo firstName de customer a actualizar no es null -> se lo asigno al customer recuperado
            Optional.ofNullable(customerRequestDTO.firstName()).ifPresent(existingCustomer::setFirstName);
            Optional.ofNullable(customerRequestDTO.lastName()).ifPresent(existingCustomer::setLastName);
            Optional.ofNullable(customerRequestDTO.email()).ifPresent(existingCustomer::setEmail);
            Optional.ofNullable(customerRequestDTO.address()).ifPresent(existingCustomer::setAddress);

            //Actualizo customer recuperado
            return customerRepository.save(existingCustomer);
        }).orElseThrow(() -> new CustomerNotFoundException(
                "Can not update customer: No customer found with the provided id"
        )); //No se encontró el customer en la bd
    }

    @Override
    public List<CustomerResponseDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsById(String id) {
        return customerRepository.existsById(id);
        /*return customerRepository.findById(id).isPresent();*/
    }

    @Override
    public CustomerResponseDTO findById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::toCustomerResponseDTO)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "No customer found with the provided id"
                ));
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
