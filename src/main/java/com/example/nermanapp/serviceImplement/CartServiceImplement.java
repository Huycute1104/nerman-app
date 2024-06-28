package com.example.nermanapp.serviceImplement;
import com.example.nermanapp.dto.Request.CartRequest.AddToCartRequest;
import com.example.nermanapp.dto.Response.CartResponse.CartResponse;
import com.example.nermanapp.repository.CartRepo;
import com.example.nermanapp.repository.ProductRepo;
import com.example.nermanapp.repository.UserRepo;
import com.example.nermanapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImplement implements CartService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;

}
