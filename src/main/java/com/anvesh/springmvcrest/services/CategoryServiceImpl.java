package com.anvesh.springmvcrest.services;

import com.anvesh.springmvcrest.api.v1.mapper.CategoryMapper;
import com.anvesh.springmvcrest.api.v1.model.CategoryDTO;
import com.anvesh.springmvcrest.domain.Category;
import com.anvesh.springmvcrest.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    //    Finding all categories in the database
    @Override
    public List<CategoryDTO> findall() {
        List<Category> categories = categoryRepository.findAll();
//        Converts catogory to category list
        return categories.stream().map(mapper::categoryToCategoryDTO).collect(Collectors.toList());
    }

    /*
     * to find categorie by name
     * if not found return null
     */
    @Override
    public CategoryDTO findByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);

        return mapper.categoryToCategoryDTO(category.orElseThrow(() -> new RuntimeException("Category Not found")));
    }
}
