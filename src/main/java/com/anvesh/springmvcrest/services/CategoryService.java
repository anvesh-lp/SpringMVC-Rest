package com.anvesh.springmvcrest.services;

import com.anvesh.springmvcrest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findall();

    CategoryDTO findByName(String name);

}
