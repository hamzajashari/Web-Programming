package com.onlineshop.onlineshop.service.impl;

import com.onlineshop.onlineshop.model.exceptions.CategoryNotFoundException;
import com.onlineshop.onlineshop.model.exceptions.ManufacturerNotFoundException;
import com.onlineshop.onlineshop.model.Category;
import com.onlineshop.onlineshop.model.Manufacturer;
import com.onlineshop.onlineshop.model.Product;
import com.onlineshop.onlineshop.model.exceptions.ProductNotFoundException;
import com.onlineshop.onlineshop.repository.jpa.CategoryRepository;
import com.onlineshop.onlineshop.repository.jpa.ManufacturerRepository;
import com.onlineshop.onlineshop.repository.jpa.ProductRepository;
import com.onlineshop.onlineshop.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
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
        return Optional.ofNullable(productRepository.findByName(name));
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
    Category category = this.categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    Manufacturer manufacturer = this.manufacturerRepository
            .findById(manufacturerId)
            .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
    this.productRepository.deleteByName(name);
    return Optional.of(this.productRepository.save(new Product(name,price,quantity,category,manufacturer) ));
    }



    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        product.setManufacturer(manufacturer);

        return Optional.of(this.productRepository.save(product));

    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
