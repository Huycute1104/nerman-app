package com.example.nermanapp.service;

import com.example.nermanapp.dto.Mapper.CartDTO;
import com.example.nermanapp.dto.Request.CartRequest.AddToCartRequest;
import com.example.nermanapp.dto.Request.CartRequest.UpdateCartRequest;
import com.example.nermanapp.dto.Response.CartResponse.CartResponse;
import com.example.nermanapp.model.Cart;

import java.util.List;

public interface CartService {
    public CartResponse addToCart(AddToCartRequest request);
    public CartResponse updateCart(int cartId, UpdateCartRequest request);
    public CartResponse deleteCartItem(int cartId);
    public List<CartDTO> getCartByUser(int userId);
    public  Cart upQuantity(int cartId);
    public  Cart downQuantity(int cartId);
    int getQuantityInCart(int customerId);
}
