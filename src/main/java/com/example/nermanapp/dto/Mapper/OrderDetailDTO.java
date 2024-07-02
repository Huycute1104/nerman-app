package com.example.nermanapp.dto.Mapper;

import com.example.nermanapp.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDTO {
    private int orderDetailId;
    private int quantity;
    private double price;
    private int itemId;
    private String itemName;
    private String CustomerName;
    private int itemTypeId;
    private OrderStatus status;
    private String itemImages;
}
