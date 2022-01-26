package com.onlineshop.onlineshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException{

    public ShoppingCartNotFoundException(Long id){
        super(String.format("Shopping Cart with id: %d was not Found",id));
    }
}
