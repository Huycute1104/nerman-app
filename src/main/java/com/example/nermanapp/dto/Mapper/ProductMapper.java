package com.example.nermanapp.dto.Mapper;


import com.example.nermanapp.model.Product;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                .productID(product.getProductID())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantitySold(product.getQuantitySold())
                .quantity(product.getQuantity())
                .status(product.isStatus())
                .productImages(product.getProductImages())
                .build();
    }
}
