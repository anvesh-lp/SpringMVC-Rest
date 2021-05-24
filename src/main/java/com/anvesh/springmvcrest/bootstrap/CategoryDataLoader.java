package com.anvesh.springmvcrest.bootstrap;

import com.anvesh.springmvcrest.domain.Category;
import com.anvesh.springmvcrest.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CategoryDataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryDataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        generateCatories().forEach(categoryRepository::save);
        System.out.println("Added categories to database");
    }

    public List<Category> generateCatories() {
        System.out.println("Generating list of categories to save in datbase");
        Category fruits = Category.builder().name("fruits").build();
        Category driedFruits = Category.builder().name("dried-fruits").build();
        Category exotic = Category.builder().name("exotic").build();
        Category nuts = Category.builder().name("nuts").build();
        Category fresh = Category.builder().name("fresh").build();
        return new ArrayList<>(List.of(fruits, driedFruits, nuts, exotic, fresh));

    }
}
