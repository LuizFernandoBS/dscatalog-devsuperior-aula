package com.devsuperior.backend.dtos;

import com.devsuperior.backend.entities.Category;
import com.devsuperior.backend.entities.Product;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Instant date;

    private Set<CategoryDTO> categoryDTOS = new HashSet<>();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price,
                      String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getImgUrl(), product.getDate());
    }

    public ProductDTO(Product product, Set<Category> categories) {
        this(product);
        categories.forEach(category ->
                this.getCategoryDTOS().add(new CategoryDTO(category)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Set<CategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }
}
