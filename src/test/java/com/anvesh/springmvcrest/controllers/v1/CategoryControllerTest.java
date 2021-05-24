package com.anvesh.springmvcrest.controllers.v1;

import com.anvesh.springmvcrest.api.v1.model.CategoryDTO;
import com.anvesh.springmvcrest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {


    public static final String name = "anvesh";
    @Mock
    CategoryService service;

    @InjectMocks
    CategoryController controller;
    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllCategories() throws Exception {
        List<CategoryDTO> categoryDTOList = new ArrayList<>(List.of(new CategoryDTO(), new CategoryDTO()));
        when(service.findall()).thenReturn(categoryDTOList);
        mvc.perform(get("/api/v1/categories/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryDTOs", hasSize(2)));
    }

    @Test
    void getCategorie() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName(name);
        when(service.findByName(anyString())).thenReturn(categoryDTO);
        mvc.perform(get("/api/v1/categories/" + name).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.id", equalTo(1)));

    }
}