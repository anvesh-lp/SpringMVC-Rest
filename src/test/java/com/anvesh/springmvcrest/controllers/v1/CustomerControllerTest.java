package com.anvesh.springmvcrest.controllers.v1;

import com.anvesh.springmvcrest.api.v1.model.CustomerDTO;
import com.anvesh.springmvcrest.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest extends AbstractClassJson {

    public static final String name = "anvesh";
    public static final long id = 1L;
    @InjectMocks
    CustomerController controller;
    @Mock
    CustomerService customerService;

    MockMvc mvc;


    @BeforeEach
    void setUp() {
//        CustomerService=new CustomerServiceImpl()
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllCustomers() throws Exception {
        List<CustomerDTO> customers = List.of(new CustomerDTO(), new CustomerDTO());
        when(customerService.findAll()).thenReturn(customers);
        mvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerDTOs", hasSize(2)));
    }

    @Test
    void getCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(id);
        customerDTO.setFirstname(name);
        when(customerService.findById(anyLong())).thenReturn(customerDTO);
        mvc.perform(get("/api/v1/customers/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(name)));
//                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void createCustomer() throws Exception {
        CustomerDTO dt = new CustomerDTO();
        dt.setFirstname(name);
        dt.setLastname(name + " kunuguntla");
        CustomerDTO savedcustomer = new CustomerDTO();
//        savedcustomer.setId(1L);
        savedcustomer.setFirstname(name);
        savedcustomer.setCustomer_url("/api/v1/customers/1");
        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(savedcustomer);
        mvc.perform(post("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dt)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(name)))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(name);
        customerDTO.setCustomer_url("/api/v1/1");
        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTO);
        mvc.perform(put("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(customerDTO.getFirstname())))
                .andExpect(jsonPath("$.customer_url", equalTo(customerDTO.getCustomer_url())));
    }

    @Test
    void deleteCustomer() throws Exception {
        mvc.perform(delete("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService, times(1)).deleteCustomer(anyLong());

    }
}