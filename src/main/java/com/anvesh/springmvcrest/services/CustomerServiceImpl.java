package com.anvesh.springmvcrest.services;

import com.anvesh.springmvcrest.api.v1.mapper.CustomerMapper;
import com.anvesh.springmvcrest.api.v1.model.CustomerDTO;
import com.anvesh.springmvcrest.repositories.CustomerRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRespository customerRespository;
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRespository customerRespository, CustomerMapper mapper) {
        this.customerRespository = customerRespository;
        this.mapper = mapper;
    }

    //    Returning all customers from database
    @Override
    public List<CustomerDTO> findAll() {
        return customerRespository.findAll()
                .stream().map(mapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    /*
     * Find by Customer id
     * Throw Runtime Exception if customer is not found
     */
    @Override
    public CustomerDTO findById(Long id) {
        return mapper.customerToCustomerDto(customerRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found")));
    }
}
