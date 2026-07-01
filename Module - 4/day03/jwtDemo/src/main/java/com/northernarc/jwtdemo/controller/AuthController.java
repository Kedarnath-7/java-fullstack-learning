package com.northernarc.jwtdemo.controller;

import com.northernarc.jwtdemo.dto.JwtRequestDTO;
import com.northernarc.jwtdemo.dto.JwtResponseDTO;
import com.northernarc.jwtdemo.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/auth/login")
    public JwtResponseDTO login(@RequestBody JwtRequestDTO jwtRequest) {
        Authentication authentication =new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                jwtRequest.getPassword());
        authenticationManager.authenticate(authentication);

        //jwtUtil.generateToken(jwtRequest.getUsername());
        JwtResponseDTO jwtResponse =new JwtResponseDTO();
        jwtResponse.setToken(jwtUtil.generateToken(jwtRequest.getUsername()));
        return jwtResponse;
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String user() {
        return "Hello User!";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin!";
    }
}
