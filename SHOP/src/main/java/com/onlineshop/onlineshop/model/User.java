package com.onlineshop.onlineshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
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

    public User() {
    }
}
