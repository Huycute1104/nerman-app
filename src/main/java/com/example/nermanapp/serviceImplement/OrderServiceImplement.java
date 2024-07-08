package com.example.nermanapp.serviceImplement;

import com.example.nermanapp.dto.Mapper.OrderDTO;
import com.example.nermanapp.dto.Mapper.OrderMapper;
import com.example.nermanapp.dto.Request.OrderRequest.CreateOrderRequest;
import com.example.nermanapp.dto.Response.OrderResponse.OrderResponse;
import com.example.nermanapp.enums.OrderStatus;
import com.example.nermanapp.model.Order;
import com.example.nermanapp.model.OrderDetail;
import com.example.nermanapp.model.Product;
import com.example.nermanapp.model.User;
import com.example.nermanapp.repository.*;
import com.example.nermanapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public List<OrderDTO> getOrderForCustomer(int id) {
        return orderRepo.findByUserUsersID(id)
                .stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }


    @Override
    public OrderResponse createOrderWithDetails(CreateOrderRequest request) {
        var user = userRepo.findUserByUsersID(request.getCustomerId()).orElse(null);
        if (user == null) {
            return OrderResponse.builder()
                    .order(null)
                    .statusCode(404)
                    .status("User Not Found")
                    .build();

        }
        if (request.getProducts() == null || request.getProducts().isEmpty()) {
            return OrderResponse.builder()
                    .status("No product provided in the request")
                    .order(null)
                    .orderDetails(null)
                    .build();
        }
        double calculatedTotal = 0;
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CreateOrderRequest.OrderProductRequest productRequest : request.getProducts()) {
            var product = productRepo.findById(productRequest.getProductId()).orElse(null);
            if (product == null) {
                return OrderResponse.builder()
                        .status("Product with ID " + productRequest.getProductId() + " not found")
                        .order(null)
                        .orderDetails(null)
                        .statusCode(404)
                        .build();
            }

            if (product.getQuantity() < productRequest.getQuantity()) {
                return OrderResponse.builder()
                        .status("Insufficient quantity for product with ID " + productRequest.getProductId())
                        .order(null)
                        .orderDetails(null)
                        .statusCode(409)
                        .build();
            }
            double itemTotal = (product.getPrice()) * productRequest.getQuantity();
            System.out.println(itemTotal);
            calculatedTotal += itemTotal;
            System.out.println(calculatedTotal);

            OrderDetail orderDetail = OrderDetail.builder()
                    .product(product)
                    .quantity(productRequest.getQuantity())
                    .price(itemTotal)
                    .build();
            orderDetails.add(orderDetail);
        }
        System.out.println(calculatedTotal);
        System.out.println(request.getTotal());
//        if (request.getTotal() <= 0
//                || request.getTotal() != calculatedTotal
//        ) {
//            return OrderResponse.builder()
//                    .status("Invalid total value")
//                    .order(null)
//                    .orderDetails(null)
//                    .statusCode(400)
//                    .build();
//        }

        Order order = Order.builder()
                .orderDate(new Date())
                .total(request.getTotal())
                .orderStatus(OrderStatus.PENDING)
                .user(user)
                .customerAddress(request.getCustomerAddress())
                .customerName(request.getCustomerName())
                .customerPhone(request.getCustomerPhone())
                .build();
        orderRepo.save(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(order);
            orderDetailRepo.save(orderDetail);

            // Update item buy count and quantity
            Product product = orderDetail.getProduct();
            int currentQuantitySold = Optional.of(product.getQuantitySold()).orElse(0);
            product.setQuantitySold(currentQuantitySold + orderDetail.getQuantity());
            product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
            productRepo.save(product);

        }

        return OrderResponse.builder()
                .status("Order Created Successfully with Order Details")
                .order(order)
                .orderDetails(orderDetails)
                .statusCode(200)
                .build();
    }

}
