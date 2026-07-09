package com.northernarc.codingassessment5.controller;

import com.northernarc.codingassessment5.dto.AuthResponse;
import com.northernarc.codingassessment5.dto.LoginRequest;
import com.northernarc.codingassessment5.dto.RegisterRequest;
import com.northernarc.codingassessment5.service.CustomerService;
import com.northernarc.codingassessment5.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    public AuthController(CustomerService customerService, JwtUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return null;
    }
}
