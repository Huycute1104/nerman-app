package com.example.nermanapp.controller;

import com.example.nermanapp.dto.Mapper.OrderDTO;
import com.example.nermanapp.dto.Request.OrderRequest.CreateOrderRequest;
import com.example.nermanapp.dto.Response.OrderResponse.OrderResponse;
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

    @PostMapping("")
    public ResponseEntity<OrderResponse> createOrderWithDetails(@RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrderWithDetails(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDTO>> getOrderForCustomer(@PathVariable int id) {
        List<OrderDTO> orders = orderService.getOrderForCustomer(id);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(orders);
        }
    }

}
