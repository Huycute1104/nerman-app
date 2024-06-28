package com.example.nermanapp.cotroller;

import com.example.nermanapp.dto.Request.CartRequest.AddToCartRequest;
import com.example.nermanapp.dto.Request.CartRequest.UpdateCartRequest;
import com.example.nermanapp.dto.Response.CartResponse.CartResponse;
import com.example.nermanapp.model.Cart;
import com.example.nermanapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    @Autowired
    private CartService cartService;


}
