package com.example.nermanapp.repository;


import com.example.nermanapp.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail,Integer> {

}
