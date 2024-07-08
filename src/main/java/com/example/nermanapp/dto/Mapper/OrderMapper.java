package com.example.nermanapp.dto.Mapper;

import com.example.nermanapp.model.Order;
import com.example.nermanapp.model.OrderDetail;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toOrderDTO(Order order) {
        Set<OrderDetailDTO> orderDetailDTOs = order.getOrderDetails().stream()
                .map(OrderMapper::toOrderDetailDTO)
                .collect(Collectors.toSet());

        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .total(order.getTotal())
                .orderStatus(order.getOrderStatus().name())
                .orderDetails(orderDetailDTOs)
                .customerName(order.getCustomerName())
                .customerPhone(order.getCustomerPhone())
                .customerAddress(order.getCustomerAddress())
                .build();
    }

    public static OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail) {
        return OrderDetailDTO.builder()
                .orderDetailId(orderDetail.getOrderDetailId())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .itemId(orderDetail.getProduct().getProductID())
                .itemName(orderDetail.getProduct().getProductName())
                .CustomerName(orderDetail.getOrder().getCustomerName())
                .status(orderDetail.getOrder().getOrderStatus())
                .itemImages(orderDetail.getProduct().getProductImages().get(0).getImageUrl())
                .build();
    }
}
