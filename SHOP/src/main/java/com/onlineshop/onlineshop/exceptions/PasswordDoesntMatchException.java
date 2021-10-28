package com.onlineshop.onlineshop.exceptions;

public class PasswordDoesntMatchException extends RuntimeException{
    public PasswordDoesntMatchException() {
        super("Password doesnt match!!");
    }
}
