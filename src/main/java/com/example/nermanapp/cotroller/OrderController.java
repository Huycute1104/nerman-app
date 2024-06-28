package com.example.nermanapp.cotroller;

import com.example.nermanapp.dto.Request.OrderRequest.CreateOrderRequest;
import com.example.nermanapp.dto.Response.OrderResponse.OrderResponse;
import com.example.nermanapp.model.Order;
import com.example.nermanapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;
}
