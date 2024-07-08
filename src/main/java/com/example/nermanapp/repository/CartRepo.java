package com.example.nermanapp.repository;

import com.example.nermanapp.model.Cart;
import com.example.nermanapp.model.Product;
import com.example.nermanapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Integer> {
    Optional<Cart> findCartByUserAndProduct(User user, Product product);
    Optional<Cart> findCartByCartID(int cartId);
    List<Cart> findByUser(User user);
    int countCartByUser(User user);
}
