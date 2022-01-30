package com.onlineshop.onlineshop.repository.jpa;

import com.onlineshop.onlineshop.model.ShoppingCart;
import com.onlineshop.onlineshop.model.User;
import com.onlineshop.onlineshop.model.enumeration.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    public Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus shoppingCartStatus);
}
