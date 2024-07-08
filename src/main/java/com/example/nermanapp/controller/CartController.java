package com.example.nermanapp.controller;

import com.example.nermanapp.dto.Mapper.CartDTO;
import com.example.nermanapp.dto.Request.CartRequest.AddToCartRequest;
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
    @PostMapping("")
    //    @PreAuthorize("hasAuthority('customer:create')")
    public CartResponse createCategory(@RequestBody AddToCartRequest request) {
        return cartService.addToCart(request);
    }
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin:read')")
    public List<CartDTO> getCartByUser(@PathVariable int id) {
        return cartService.getCartByUser(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CartResponse> deleteFood(@PathVariable int id) {
        CartResponse response = cartService.deleteCartItem(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/up/{id}")
    public ResponseEntity<?> increaseCartQuantity(@PathVariable int id) {
        try {
            Cart updatedCart = cartService.upQuantity(id);
            return ResponseEntity.ok(updatedCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Cart not found");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("Quantity exceeds available stock");
        }
    }
    @PutMapping("/down/{id}")
    public ResponseEntity<?> decreaseCartQuantity(@PathVariable int id) {
        try {
            Cart updatedCart = cartService.downQuantity(id);
            return ResponseEntity.ok(updatedCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("CartItem not found");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(406).body("Quantity cannot be less than 1");
        }
    }

    @GetMapping("/quantityCart/{customerId}")
    public ResponseEntity<Integer> getQuantityInCart(@PathVariable int customerId) {
        int quantity = cartService.getQuantityInCart(customerId);
        return ResponseEntity.ok(quantity);
    }

}
