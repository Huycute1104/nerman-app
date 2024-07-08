package com.example.nermanapp.repository;


import com.example.nermanapp.model.Order;
import com.example.nermanapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findByUserUsersID(int id);
}
