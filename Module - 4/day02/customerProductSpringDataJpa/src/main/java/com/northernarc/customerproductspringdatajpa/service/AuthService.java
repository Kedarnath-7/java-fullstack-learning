package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.dto.AuthLoginRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.AuthRegisterRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.AuthResponseDTO;
import com.northernarc.customerproductspringdatajpa.exceptions.ValidationException;
import com.northernarc.customerproductspringdatajpa.model.Customer;
import com.northernarc.customerproductspringdatajpa.model.CustomerRole;
import com.northernarc.customerproductspringdatajpa.repository.CustomerRepository;
import com.northernarc.customerproductspringdatajpa.utility.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public AuthService(CustomerRepository customerRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO register(AuthRegisterRequestDTO request) {
        if (customerRepository.findByEmailIgnoreCase(request.getEmail()).isPresent()) {
            throw new ValidationException("Email is already registered");
        }

        Customer customer = new Customer();
        customer.setFName(request.getFName());
        customer.setLName(request.getLName());
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setRole(CustomerRole.USER);

        Customer savedCustomer = customerRepository.save(customer);
        String token = jwtUtil.generateToken(savedCustomer.getEmail());
        return new AuthResponseDTO(token, savedCustomer.getId(), savedCustomer.getEmail(), savedCustomer.getRole().name());
    }

    public AuthResponseDTO login(AuthLoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Customer customer = customerRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new ValidationException("Invalid credentials"));

        String token = jwtUtil.generateToken(customer.getEmail());
        return new AuthResponseDTO(token, customer.getId(), customer.getEmail(), customer.getRole().name());
    }
}
