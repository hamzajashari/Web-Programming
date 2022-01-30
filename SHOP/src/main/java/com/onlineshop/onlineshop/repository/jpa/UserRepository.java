package com.onlineshop.onlineshop.repository.jpa;

import com.onlineshop.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username,String Password);
}
