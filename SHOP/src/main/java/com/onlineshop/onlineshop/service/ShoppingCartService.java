package com.onlineshop.onlineshop.service;

import com.onlineshop.onlineshop.model.Product;
import com.onlineshop.onlineshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username,Long prodId);
}
