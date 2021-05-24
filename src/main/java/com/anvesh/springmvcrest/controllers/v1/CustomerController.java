package com.anvesh.springmvcrest.controllers.v1;

import com.anvesh.springmvcrest.api.v1.model.CustomerDTO;
import com.anvesh.springmvcrest.api.v1.model.CustomerDTOList;
import com.anvesh.springmvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    private final CustomerService customeRepo;

    public CustomerController(CustomerService customeRepo) {
        this.customeRepo = customeRepo;
    }


    @GetMapping
    public ResponseEntity<CustomerDTOList> getAllCustomers() {
        return new ResponseEntity<CustomerDTOList>(new CustomerDTOList(customeRepo.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(customeRepo.findById(id), HttpStatus.OK);
    }

}
