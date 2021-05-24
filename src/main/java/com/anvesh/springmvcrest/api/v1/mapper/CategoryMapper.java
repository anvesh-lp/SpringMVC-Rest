package com.anvesh.springmvcrest.api.v1.mapper;

import com.anvesh.springmvcrest.api.v1.model.CategoryDTO;
import com.anvesh.springmvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//To make this mapper as a component
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper instance = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
