package com.devsuperior.backend.resources;

import com.devsuperior.backend.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        Category category = new Category(1L, "Electronics");
        Category category1 = new Category(2L, "Books");
        List<Category> categories = Arrays.asList(category, category1);
        return ResponseEntity.ok().body(categories);
    }
}
