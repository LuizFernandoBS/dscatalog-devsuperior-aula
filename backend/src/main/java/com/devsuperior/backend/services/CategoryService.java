package com.devsuperior.backend.services;

import com.devsuperior.backend.dtos.CategoryDTO;
import com.devsuperior.backend.entities.Category;
import com.devsuperior.backend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll() {
        List<Category> categories = repository.findAll();
        return categories.stream()
                .map(CategoryDTO::new).toList();
    }
}
