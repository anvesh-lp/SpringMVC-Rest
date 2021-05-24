package com.anvesh.springmvcrest.api.v1.mapper;

import com.anvesh.springmvcrest.api.v1.model.CustomerDTO;
import com.anvesh.springmvcrest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerMapperTest {

    CustomerMapper mapper = CustomerMapper.CUSTOMER_MAPPER;

    @Test
    void customerToCustomerDto() {
        Customer cus1 = Customer.builder().firstname("anvesh").lastname("Kunuguntla").customer_url("anvesh.com").build();
        CustomerDTO customerDTO = mapper.customerToCustomerDto(cus1);
        assertNotNull(customerDTO);
        assertEquals("anvesh", customerDTO.getFirstname());
        assertEquals("Kunuguntla", customerDTO.getLastname());
        assertEquals("anvesh.com", customerDTO.getCustomer_url());
    }
}