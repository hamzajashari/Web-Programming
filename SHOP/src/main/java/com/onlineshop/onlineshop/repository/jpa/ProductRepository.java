package com.onlineshop.onlineshop.repository.jpa;

import com.onlineshop.onlineshop.model.Category;
import com.onlineshop.onlineshop.model.Manufacturer;
import com.onlineshop.onlineshop.model.Product;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    void deleteByName(String name);
}
