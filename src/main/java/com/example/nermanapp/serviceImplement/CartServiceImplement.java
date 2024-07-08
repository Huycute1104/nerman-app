package com.example.nermanapp.serviceImplement;

import com.example.nermanapp.dto.Mapper.CartDTO;
import com.example.nermanapp.dto.Mapper.CartMapper;
import com.example.nermanapp.dto.Request.CartRequest.AddToCartRequest;
import com.example.nermanapp.dto.Request.CartRequest.UpdateCartRequest;
import com.example.nermanapp.dto.Response.CartResponse.CartResponse;
import com.example.nermanapp.model.Cart;
import com.example.nermanapp.repository.CartRepo;
import com.example.nermanapp.repository.ProductRepo;
import com.example.nermanapp.repository.UserRepo;
import com.example.nermanapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImplement implements CartService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;

    @Override
    public CartResponse addToCart(AddToCartRequest request) {
        int customerId = request.getCustomerID();
        int productId = request.getProductID();
        var customer = userRepo.findUserByUsersID(customerId).orElse(null);
        if (customer != null) {
            var product = productRepo.findProductByProductID(productId).orElse(null);
            if (product != null) {
                var checkCart = cartRepo.findCartByUserAndProduct(customer, product).orElse(null);
                if (checkCart != null) {
                    if (checkCart.getQuantity() < product.getQuantity()) {
                        checkCart.setQuantity(checkCart.getQuantity() + 1);
                        checkCart.setPrice(checkCart.getPrice() + product.getPrice());
                        cartRepo.save(checkCart);
                        return CartResponse.builder()
                                .status("Add to cart successfully")
                                .cart(checkCart)
                                .build();
                    } else {
                        return CartResponse.builder()
                                .status("Quantity not enough")
                                .cart(checkCart)
                                .build();
                    }
                } else {
                    Cart cart = Cart.builder()
                            .user(customer)
                            .product(product)
                            .quantity(1)
                            .price(product.getPrice())
                            .build();
                    cartRepo.save(cart);
                    return CartResponse.builder()
                            .status("Add to cart successfully")
                            .cart(cart)
                            .build();
                }
            } else {
                return CartResponse.builder()
                        .status("Product not found")
                        .cart(null)
                        .build();
            }
        } else {
            return CartResponse.builder()
                    .status("Customer not found")
                    .cart(null)
                    .build();
        }
    }

    @Override
    public CartResponse updateCart(int cartId, UpdateCartRequest request) {
        return null;
    }

    @Override
    public CartResponse deleteCartItem(int cartId) {
        var cart = cartRepo.findCartByCartID(cartId).orElse(null);
        if (cart == null) {
            return CartResponse.builder()
                    .status("Product not found")
                    .cart(null)
                    .build();
        }else{
            cartRepo.delete(cart);
            return CartResponse.builder()
                    .status("Product deleted successfully")
                    .cart(null)
                    .build();
        }
    }

    @Override
    public List<CartDTO> getCartByUser(int userId) {
        var user = userRepo.findUserByUsersID(userId).orElse(null);
        if (user != null) {
            return cartRepo.findByUser(user).stream()
                    .map(CartMapper::toCartDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public Cart upQuantity(int cartId) {
        var cart = cartRepo.findCartByCartID(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        var product = cart.getProduct();

        if (cart.getQuantity() >= product.getQuantity()) {
            throw new IllegalStateException("Quantity exceeds available stock");
        }

        cart.setQuantity(cart.getQuantity() + 1);
        cart.setPrice(cart.getPrice() + product.getPrice());
        cartRepo.save(cart);
        return cart;
    }



    @Override
    public Cart downQuantity(int cartId) {
        var cart = cartRepo.findCartByCartID(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        var product = cart.getProduct();
        if (cart.getQuantity() <= 1) {
            throw new IllegalStateException("Quantity cannot be less than 1");
        }

        cart.setQuantity(cart.getQuantity() - 1);
        cart.setPrice(cart.getPrice() - product.getPrice());
        cartRepo.save(cart);
        return cart;
    }

    @Override
    public int getQuantityInCart(int customerId) {
        var customer = userRepo.findUserByUsersID(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return cartRepo.countCartByUser(customer);
    }


}
