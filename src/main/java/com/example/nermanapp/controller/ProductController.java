package com.example.nermanapp.controller;

import com.example.nermanapp.dto.Mapper.ProductDTO;
import com.example.nermanapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
//    @PreAuthorize("hasAuthority('admin:read')")
    public List<ProductDTO> getProduct() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
}
