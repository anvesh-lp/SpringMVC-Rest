package com.anvesh.springmvcrest.services;

import com.anvesh.springmvcrest.api.v1.mapper.CustomerMapper;
import com.anvesh.springmvcrest.api.v1.model.CustomerDTO;
import com.anvesh.springmvcrest.domain.Customer;
import com.anvesh.springmvcrest.repositories.CustomerRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRespository customerRespository;


    CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRespository, CustomerMapper.CUSTOMER_MAPPER);
    }

    @Test
    void findAll() {
        List<Customer> customers = List.of(
                Customer.builder().firstname("anvesh").lastname("Kunuguntla").customer_url("anvesh.com").build(),
                Customer.builder().firstname("Ganesh").lastname("puttaa").customer_url("ganesh.com").build()
        );
        when(customerRespository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOS = customerService.findAll();
        assertEquals(2, customerDTOS.size());
        assertEquals("anvesh", customerDTOS.get(0).getFirstname());
        assertEquals("Ganesh", customerDTOS.get(1).getFirstname());
    }

    @Test
    void findById() {
        Customer customer = Customer.builder().firstname("anvesh").lastname("Kunuguntla").customer_url("anvesh.com").build();
        when(customerRespository.findById(anyLong())).thenReturn(Optional.of(customer));
        CustomerDTO customerDTO = customerService.findById(1L);
        assertNotNull(customerDTO);
        assertEquals("anvesh", customerDTO.getFirstname());
        assertEquals("Kunuguntla", customerDTO.getLastname());

    }
}