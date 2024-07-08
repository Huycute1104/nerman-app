package com.example.nermanapp.dto.Mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private int id;
    private ProductCartDTO productCartDTO;
    private int quantity;
    private double price;
}
