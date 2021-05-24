package com.anvesh.springmvcrest.api.v1.mapper;

import com.anvesh.springmvcrest.api.v1.model.CategoryDTO;
import com.anvesh.springmvcrest.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryMapperTest {

    CategoryMapper instance = CategoryMapper.instance;

    @BeforeEach
    void setUp() {
    }

    @Test
    void categoryToCategoryDTO() {
        Category category = Category.builder().name("fruits").build();
        category.setId(1L);
        CategoryDTO categoryDTO = instance.categoryToCategoryDTO(category);
        assertNotNull(categoryDTO);
        assertEquals("fruits", categoryDTO.getName());
        assertEquals(1L, categoryDTO.getId());
    }
}