package com.example.nermanapp.serviceImplement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nermanapp.model.Product;
import com.example.nermanapp.repository.CategoryRepo;
import com.example.nermanapp.repository.ProductRepo;
import com.example.nermanapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FoodServiceImplement implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public List<Product> getAll() {
        return List.of();
    }

}
