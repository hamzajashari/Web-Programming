package com.onlineshop.onlineshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException{
    public ProductAlreadyInShoppingCartException(Long id) {
        super(String.format("Product with id: %id already exists in shopping cart",id));
    }
}
