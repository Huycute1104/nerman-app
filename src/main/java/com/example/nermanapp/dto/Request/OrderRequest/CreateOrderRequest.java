package com.example.nermanapp.dto.Request.OrderRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private double total;
    private int customerId;
    private String customerPhone;
    private String customerAddress;
    private String customerName;
    private List<CreateOrderRequest.OrderProductRequest> products;
    @Builder
    @Data
    public static class OrderProductRequest {
        private int productId;
        private int quantity;
    }
}
