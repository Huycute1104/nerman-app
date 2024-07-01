package com.example.nermanapp.cotroller;

import com.example.nermanapp.auth.AuthenticationRequest;
import com.example.nermanapp.auth.AuthenticationResponse;
import com.example.nermanapp.auth.AuthenticationService;
import com.example.nermanapp.config.LogoutService;
import com.example.nermanapp.dto.Request.UserRequest.CreateUserRequest;
import com.example.nermanapp.dto.Response.UserResponse.RegisterResponse;
import com.example.nermanapp.model.User;
import com.example.nermanapp.service.EmailService;
import com.example.nermanapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final LogoutService logoutService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${spring.mail.username}")
    private String sender;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(authenticationService.createUser(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);

    }

        @PostMapping("/forgot-password")
        public ResponseEntity<?> register(@RequestParam String email) {
            try {
                User user = userService.findByEmailForMail(email);
                if (user == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
                } else {
                    String pass = RandomStringUtils.randomAlphanumeric(6);

                    user.setPassword(passwordEncoder.encode(pass));
                    user = userService.saveUserForMail(user);
                    emailService.sendSimpleMessage(email, "Reset password", "New password is : " + pass);
                    return ResponseEntity.ok("New password is send in your email");
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(RegisterResponse
                        .builder()
                        .status(e.getMessage())
                        .message("Change Password fail")
                        .build());
            }


        }

}
