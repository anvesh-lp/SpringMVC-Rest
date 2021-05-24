package com.anvesh.springmvcrest.services;

import com.anvesh.springmvcrest.api.v1.mapper.CategoryMapper;
import com.anvesh.springmvcrest.api.v1.model.CategoryDTO;
import com.anvesh.springmvcrest.domain.Category;
import com.anvesh.springmvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private final String name = "fruits";
    private final Long id = 1L;
    //    @Mock
//    CategoryMapper mapper;
    @Mock
    CategoryRepository categoryRepository;
    CategoryService categoryService;

//    @InjectMocks
//    CategoryServiceImpl categoryServiceImp;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.instance);
    }

    @Test
    void findall() {
        List<Category> categories = new ArrayList<>(List.of(Category.builder().name("1").build(), new Category()));
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> categoryDTOS = categoryService.findall();
        assertEquals(2, categoryDTOS.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void findByName() {
        Category category = Category.builder().name(name).build();
        category.setId(id);
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));
        CategoryDTO categoryDTO = categoryService.findByName(name);
        assertEquals(name, categoryDTO.getName());
        assertEquals(id, categoryDTO.getId());
        verify(categoryRepository, times(1)).findByName(anyString());
    }
}