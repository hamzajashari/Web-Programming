package com.onlineshop.onlineshop.model.exceptions;

public class InvalidUserCredentialIsException extends RuntimeException{

    public InvalidUserCredentialIsException() {
        super("Invalid user credentials exception");
    }
}
