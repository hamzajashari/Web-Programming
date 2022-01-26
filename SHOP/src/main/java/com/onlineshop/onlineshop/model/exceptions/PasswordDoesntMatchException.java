package com.onlineshop.onlineshop.model.exceptions;

public class PasswordDoesntMatchException extends RuntimeException{
    public PasswordDoesntMatchException() {
        super("Password doesnt match!!");
    }
}
