package com.northernarc.customerproductspringdatajpa.controller;

import com.northernarc.customerproductspringdatajpa.dto.AuthLoginRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.AuthRegisterRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.AuthResponseDTO;
import com.northernarc.customerproductspringdatajpa.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody AuthRegisterRequestDTO request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthLoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
