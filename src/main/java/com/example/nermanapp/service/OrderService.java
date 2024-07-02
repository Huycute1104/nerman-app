package com.example.nermanapp.service;


import com.example.nermanapp.dto.Mapper.OrderDTO;
import com.example.nermanapp.dto.Request.OrderRequest.CreateOrderRequest;
import com.example.nermanapp.dto.Response.OrderResponse.OrderResponse;
import com.example.nermanapp.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrderForCustomer(int id);
    public OrderResponse createOrderWithDetails(CreateOrderRequest request);
}
