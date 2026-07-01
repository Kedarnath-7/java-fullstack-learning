package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.dto.CustomerRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.CustomerResponseDTO;
import com.northernarc.customerproductspringdatajpa.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO addCustomer(CustomerRequestDTO customer);
    CustomerResponseDTO findById(Long id);
    List<CustomerResponseDTO> findAllCustomers();
    void updateById(Long id, CustomerRequestDTO customer);
    void deleteById(Long id);
    void deleteAllCustomers();

}
