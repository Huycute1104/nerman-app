package com.example.nermanapp.serviceImplement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nermanapp.dto.Mapper.ProductDTO;
import com.example.nermanapp.dto.Mapper.ProductMapper;
import com.example.nermanapp.dto.Request.ProductRequest.ProductRequest;
import com.example.nermanapp.dto.Response.ProductResponse.ProductResponse;
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
import java.util.stream.Collectors;

@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public List<ProductDTO> getAll() {
        return productRepo.findAll().stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProductResponse create(ProductRequest product) {
        var category = categoryRepo.findById(product.getCategoryId()).orElse(null);
        if (category == null) {
            return ProductResponse.builder()
                    .product(null)
                    .status("Category Not Found")
                    .build();
        }
        Product productNew = Product.builder()
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .quantitySold(0)
                .status(true)
                .category(category)
                .build();
        productRepo.save(productNew);
        return ProductResponse.builder()
                .product(productNew)
                .status("Create Product Success")
                .build();
    }

    @Override
    public ProductDTO getProductById(int id) {
        return productRepo.findById(id)
                .map(ProductMapper::toProductDTO)
                .orElse(null);
    }
}
