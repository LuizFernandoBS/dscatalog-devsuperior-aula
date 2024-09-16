package com.devsuperior.backend.services;

import com.devsuperior.backend.dtos.CategoryDTO;
import com.devsuperior.backend.entities.Category;
import com.devsuperior.backend.repositories.CategoryRepository;
import com.devsuperior.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> categories = repository.findAll();
        return categories.stream()
                .map(CategoryDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category category = obj.orElseThrow(() ->
                new ResourceNotFoundException("Resource not found " + id));
        return new CategoryDTO(category);
    }
}
