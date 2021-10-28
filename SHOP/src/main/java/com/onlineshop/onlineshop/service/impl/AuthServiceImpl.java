package com.onlineshop.onlineshop.service.impl;

import com.onlineshop.onlineshop.exceptions.InvalidArgumentsException;
import com.onlineshop.onlineshop.exceptions.InvalidUserCredentialIsException;
import com.onlineshop.onlineshop.exceptions.PasswordDoesntMatchException;
import com.onlineshop.onlineshop.model.User;
import com.onlineshop.onlineshop.repository.InMemoryUserRepository;
import com.onlineshop.onlineshop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final InMemoryUserRepository UserRepository;

    public AuthServiceImpl(InMemoryUserRepository userRepository) {
        UserRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return UserRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialIsException::new);
    }

    @Override
    public User register(String username, String password, String repeatpassword, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        if(!password.equals(repeatpassword)){
            throw new PasswordDoesntMatchException();
        }
        User user=new User(username,password,name,surname);
      return UserRepository.saveOrUpdate(user);
    }
}
