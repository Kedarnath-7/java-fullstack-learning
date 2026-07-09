package com.northernarc.codingassessment5.service.impl;

import com.northernarc.codingassessment5.model.Customer;
import com.northernarc.codingassessment5.repository.CustomerRepository;
import com.northernarc.codingassessment5.service.CustomerService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return null;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
    }

    @Override
    public Customer findByEmail(String email) {
        return null;
    }
}
