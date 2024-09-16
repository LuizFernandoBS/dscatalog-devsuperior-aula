package com.devsuperior.backend.dtos;

import com.devsuperior.backend.entities.Category;

public class CategoryDTO {

    private Long id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }
}
