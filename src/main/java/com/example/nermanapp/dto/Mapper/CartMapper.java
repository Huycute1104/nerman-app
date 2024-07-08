package com.example.nermanapp.dto.Mapper;

import com.example.nermanapp.model.Cart;
import com.example.nermanapp.model.Product;

public class CartMapper {
    public static CartDTO toCartDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getCartID())
                .productCartDTO(toProductCartDTO(cart.getProduct()))
                .quantity(cart.getQuantity())
                .price(cart.getPrice())
                .build();
    }

    private static ProductCartDTO toProductCartDTO(Product product) {
        return ProductCartDTO.builder()
                .productID(product.getProductID())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .productImages(product.getProductImages())
                .build();
    }
}
