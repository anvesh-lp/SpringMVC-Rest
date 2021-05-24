package com.anvesh.springmvcrest.services;

import com.anvesh.springmvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAll();

    CustomerDTO findById(Long Id);
}
