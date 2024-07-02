package com.example.nermanapp.service;

import com.example.nermanapp.dto.Request.UserRequest.UpdatePasswordRequest;
import com.example.nermanapp.dto.Request.UserRequest.UpdateUserRequest;
import com.example.nermanapp.dto.Response.UserResponse.UserResponse;
import com.example.nermanapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public UserResponse banUser(int userId);
    public UserResponse unbanUser(int userId);
    public UserResponse updateUser(int userId, UpdateUserRequest request );
    public UserResponse updatePassword(int userId, UpdatePasswordRequest request );
    public List<User> getALL();
    public List<User> getCustomer();
    public List<User> getStaff();
    public Optional<User> getUserById(int id);
    User saveUserForMail(User user);
    User findByEmailForMail(String email);
}
