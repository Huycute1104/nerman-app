package com.example.nermanapp.dto.Mapper;

import com.example.nermanapp.model.ProductImages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int productID;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private int quantitySold;
    private boolean status;
    private List<ProductImages> productImages;
}
