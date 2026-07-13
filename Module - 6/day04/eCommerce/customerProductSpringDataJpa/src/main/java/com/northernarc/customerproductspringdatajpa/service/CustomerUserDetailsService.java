package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.repository.CustomerRepository;
import com.northernarc.customerproductspringdatajpa.security.CustomerPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public CustomerUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmailIgnoreCase(username)
                .map(CustomerPrincipal::fromCustomer)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
