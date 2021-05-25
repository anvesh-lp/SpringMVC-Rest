package com.anvesh.springmvcrest.services;

import com.anvesh.springmvcrest.api.v1.mapper.CustomerMapper;
import com.anvesh.springmvcrest.api.v1.model.CustomerDTO;
import com.anvesh.springmvcrest.domain.Customer;
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
        System.out.println(customerRespository.findById(id));
        return mapper.customerToCustomerDto(customerRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found")));
    }

    /*
     * getting customer from user(no id)
     * converting into customer dto and saving it to db(id generated
     * converting it to customerdto and adding url
     */
    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.customerDtoToCustomer(customerDTO);
        return savedandreturn(customer);
    }


    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = mapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        return savedandreturn(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRespository.deleteById(id);
    }

    //to save adn return the customer
    private CustomerDTO savedandreturn(Customer customer) {
        Customer savedCusomter = customerRespository.save(customer);
        CustomerDTO savedDto = mapper.customerToCustomerDto(savedCusomter);
        savedDto.setCustomer_url("/api/v1/customers/" + savedCusomter.getId());
        return savedDto;
    }

}
