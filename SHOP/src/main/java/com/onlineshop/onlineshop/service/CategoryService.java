package com.onlineshop.onlineshop.service;

import com.onlineshop.onlineshop.model.Category;

import java.util.List;

public interface CategoryService {

    //Business Layer

    Category create(String name,String description);
    Category update(String name,String description);
    void delete(String name);
    List<Category> listCategories();
    List<Category> searchcategories(String searchText);
}
