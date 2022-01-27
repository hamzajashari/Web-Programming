package com.onlineshop.onlineshop.service.impl;

import com.onlineshop.onlineshop.model.Product;
import com.onlineshop.onlineshop.model.ShoppingCart;
import com.onlineshop.onlineshop.model.User;
import com.onlineshop.onlineshop.model.enumeration.ShoppingCartStatus;
import com.onlineshop.onlineshop.model.exceptions.ProductAlreadyInShoppingCartException;
import com.onlineshop.onlineshop.model.exceptions.ProductNotFoundException;
import com.onlineshop.onlineshop.model.exceptions.ShoppingCartNotFoundException;
import com.onlineshop.onlineshop.model.exceptions.UserNotFoundException;
import com.onlineshop.onlineshop.repository.InMemoryShoppingCartRepository;
import com.onlineshop.onlineshop.repository.InMemoryUserRepository;
import com.onlineshop.onlineshop.service.ProductService;
import com.onlineshop.onlineshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository userRepository;
    private final ProductService productService;
    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository, InMemoryUserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent()){
            throw new ShoppingCartNotFoundException(cartId);
        }

        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.shoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(()-> {
                    User user = this.userRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long prodId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(prodId)
                .orElseThrow(() -> new ProductNotFoundException(prodId));
        if(shoppingCart.getProducts()
                .stream()
                .filter(i->i.getId().equals(prodId))
                .collect(Collectors.toList()).size() > 0 )
            throw new ProductAlreadyInShoppingCartException(prodId);

        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}