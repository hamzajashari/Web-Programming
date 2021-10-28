package com.onlineshop.onlineshop.service;

import com.onlineshop.onlineshop.model.User;

public interface AuthService {

    User login(String username,String password);
    User register(String username,String password,String repeatpassword,String name,String surname);

}
