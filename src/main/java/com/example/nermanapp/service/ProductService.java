package com.example.nermanapp.service;

import com.example.nermanapp.dto.Request.ProductRequest.ProductRequest;
import com.example.nermanapp.dto.Response.ProductResponse.ProductResponse;
import com.example.nermanapp.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAll();
    public ProductResponse create(ProductRequest product);

}

