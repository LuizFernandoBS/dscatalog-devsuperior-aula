package com.devsuperior.backend.services;

import com.devsuperior.backend.dtos.ProductDTO;
import com.devsuperior.backend.entities.Category;
import com.devsuperior.backend.entities.Product;
import com.devsuperior.backend.repositories.CategoryRepository;
import com.devsuperior.backend.repositories.ProductRepository;
import com.devsuperior.backend.services.exceptions.DatabaseException;
import com.devsuperior.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> pagedProducts = repository.findAll(pageable);
        return pagedProducts.map(product ->
                new ProductDTO(product, product.getCategories()));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product product = obj.orElseThrow(() ->
                new ResourceNotFoundException("Entity not found: " + id));
        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = dtoToEntity(new Product(), dto);
        product = repository.save(product);
        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product;
        try {
            product = repository.getOne(id);
            product = repository.save(dtoToEntity(product, dto));
            return new ProductDTO(product, product.getCategories());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Resource not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation: " + id);
        }
    }

    private Product dtoToEntity(Product product, ProductDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        product.setDate(dto.getDate());
        product.getCategories().clear();
        dto.getCategoryDTOS().forEach(categoryDto -> {
            Category category = categoryRepository.getOne(categoryDto.getId());
            product.getCategories().add(category);
        });
        return product;
    }
}
