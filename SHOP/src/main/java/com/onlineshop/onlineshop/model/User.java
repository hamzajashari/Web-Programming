package com.onlineshop.onlineshop.model;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String name;
    private String surename;

    public User(String username, String password, String name, String surename) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surename = surename;
    }
}
