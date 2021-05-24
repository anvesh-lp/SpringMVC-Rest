package com.anvesh.springmvcrest.bootstrap;

import com.anvesh.springmvcrest.domain.Customer;
import com.anvesh.springmvcrest.repositories.CustomerRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDataLoader implements CommandLineRunner {
    private final CustomerRespository customerRespository;

    public CustomerDataLoader(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }

    @Override
    public void run(String... args) throws Exception {

        customerRespository.saveAll(getCustomers());
        System.out.println("Customers saved to database");

    }

    public List<Customer> getCustomers() {
        System.out.println("Loading customers to initialize database");
        Customer cus1 = Customer.builder().firstname("anvesh").lastname("Kunuguntla").customer_url("anvesh.com").build();
        Customer cus2 = Customer.builder().firstname("Ganesh").lastname("puttaa").customer_url("ganesh.com").build();
        Customer cus3 = Customer.builder().firstname("Chandu").lastname("Satti").customer_url("chandu.com").build();
        Customer cus4 = Customer.builder().firstname("Vishnu").lastname("dhakkili").customer_url("vishnu.com").build();
        Customer cus5 = Customer.builder().firstname("Dastagiri").lastname("palla").customer_url("dastagiri.com").build();
        return List.of(cus1, cus2, cus3, cus4, cus5);
    }
}
