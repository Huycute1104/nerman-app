package com.example.nermanapp.dto.Response.OrderResponse;

import com.example.nermanapp.model.Order;
import com.example.nermanapp.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String status;
    private Order order;
    private List<OrderDetail> orderDetails;
    private int statusCode;
}
