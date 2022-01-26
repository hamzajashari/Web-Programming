package com.onlineshop.onlineshop.service.impl;

import com.onlineshop.onlineshop.model.exceptions.CategoryNotFoundException;
import com.onlineshop.onlineshop.model.exceptions.ManufacturerNotFoundException;
import com.onlineshop.onlineshop.model.Category;
import com.onlineshop.onlineshop.model.Manufacturer;
import com.onlineshop.onlineshop.model.Product;
import com.onlineshop.onlineshop.repository.InMemoryCategoryRepository;
import com.onlineshop.onlineshop.repository.InMemoryManufacturerRepository;
import com.onlineshop.onlineshop.repository.InMemoryProductRepository;
import com.onlineshop.onlineshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final InMemoryProductRepository productRepository;
    private final InMemoryCategoryRepository categoryRepository;
    private final InMemoryManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(InMemoryProductRepository productRepository, InMemoryCategoryRepository categoryRepository, InMemoryManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

    Category category = this.categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    Manufacturer manufacturer = this.manufacturerRepository
            .findById(manufacturerId)
            .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

    return this.productRepository.save(name,price,quantity,category,manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
