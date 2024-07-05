package com.example.nermanapp.repository;

import com.example.nermanapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    Optional<Product> findProductByProductID(int id);
    Optional<Product> findById(int id);
}
