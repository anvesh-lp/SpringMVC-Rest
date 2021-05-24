package com.anvesh.springmvcrest.controllers.v1;


import com.anvesh.springmvcrest.api.v1.model.CategoryDTO;
import com.anvesh.springmvcrest.api.v1.model.CategoryDTOList;
import com.anvesh.springmvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {
    private final CategoryService categoryRepository;

    public CategoryController(CategoryService categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<CategoryDTOList> getAllCategories() {
        return new ResponseEntity<CategoryDTOList>(new CategoryDTOList(categoryRepository.findall()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategorie(@PathVariable String name) {
        return new ResponseEntity<CategoryDTO>(categoryRepository.findByName(name), HttpStatus.OK);
    }

}
