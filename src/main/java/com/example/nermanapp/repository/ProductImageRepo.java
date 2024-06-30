package com.example.nermanapp.repository;

import com.example.nermanapp.model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<ProductImages,Integer> {
}
