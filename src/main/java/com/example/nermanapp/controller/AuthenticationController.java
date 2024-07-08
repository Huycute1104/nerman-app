package com.example.nermanapp.controller;

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
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
            User user = userService.findByEmailForMail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
            } else {
                String pass = RandomStringUtils.randomAlphanumeric(6);
                user.setPassword(passwordEncoder.encode(pass));
                user = userService.saveUserForMail(user);

                String htmlBody = "<body data-new-gr-c-s-loaded=\"14.1130.0\">" +
                        "<div class=\"es-wrapper-color\">" +
                        "<!--[if gte mso 9]>" +
                        "<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">" +
                        "<v:fill type=\"tile\" color=\"#f1f1f1\"></v:fill>" +
                        "</v:background>" +
                        "<![endif]-->" +
                        "<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td class=\"esd-email-paddings\" valign=\"top\">" +
                        "<table class=\"es-content esd-footer-popover\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td class=\"esd-stripe\" esd-custom-block-id=\"7388\" align=\"center\">" +
                        "<table class=\"es-content-body\" style=\"background-color: #333333;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#333333\" align=\"center\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td class=\"esd-structure esd-checked es-p40\" style=\"background-image: url(https://demo.stripocdn.email/content/guids/636f8fab-2505-47a8-acc2-66809905a3be/images/gcb16b5ff24b12621f0ef7892066d6bcfcdc4f304e71a069a82c5dea4024805018a096ea5dc5d023ac9e06d399b11dd4f_640.jpeg); background-repeat: no-repeat; background-position: left top; background-color: #3f3e3e;\" align=\"left\" background=\"https://demo.stripocdn.email/content/guids/636f8fab-2505-47a8-acc2-66809905a3be/images/gcb16b5ff24b12621f0ef7892066d6bcfcdc4f304e71a069a82c5dea4024805018a096ea5dc5d023ac9e06d399b11dd4f_640.jpeg\" bgcolor=\"#3f3e3e\">" +
                        "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td class=\"esd-container-frame\" width=\"520\" valign=\"top\" align=\"center\">" +
                        "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"center\" class=\"esd-block-text es-p40t es-p40b\">" +
                        "<h1 style=\"color: #fefafa; font-size: 41px; text-align: center; line-height: 150%;\">WELCOME<strong></strong></h1>" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>" +
                        "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td class=\"esd-structure es-p20t es-p40r es-p40l\" align=\"left\">" +
                        "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td width=\"520\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">" +
                        "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td esdev-links-color=\"#757575\" class=\"esd-block-text es-p10t es-p40b es-p30r es-p30l\" align=\"center\">" +
                        "<p style=\"color: #f1f0f0; font-size: 16px; line-height: 200%;\"><strong></strong></p>" +
                        "<p style=\"color: #ffffff; font-size: 20px; line-height: 200%;\"><strong><span style=\"font-size: 25px; line-height: 200%;\">New password is: " + pass + "</span></strong></p>" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>" +
                        "</div>" +
                        "</body>";

                emailService.sendHtmlMessage(email, "Reset password", htmlBody);
                return ResponseEntity.ok("New password is sent to your email");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(RegisterResponse
                    .builder()
                    .status(e.getMessage())
                    .message("Change Password failed")
                    .build());
        }
    }
}
