package com.example.nermanapp.cotroller;

import com.example.nermanapp.model.Product;
import com.example.nermanapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
//    @PreAuthorize("hasAuthority('admin:read')")
    public List<Product> getAllUsers() {
        return productService.getAll();
    }


}
