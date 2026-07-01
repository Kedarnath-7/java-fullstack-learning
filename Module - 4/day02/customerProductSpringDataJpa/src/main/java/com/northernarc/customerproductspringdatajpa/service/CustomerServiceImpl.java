package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.dto.CustomerRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.CustomerResponseDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderSummaryDTO;
import com.northernarc.customerproductspringdatajpa.exceptions.CustomerNotFound;
import com.northernarc.customerproductspringdatajpa.model.Customer;
import com.northernarc.customerproductspringdatajpa.model.Order;
import com.northernarc.customerproductspringdatajpa.repository.CustomerRepository;
import com.northernarc.customerproductspringdatajpa.repository.OrderRepository;
import com.northernarc.customerproductspringdatajpa.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository){
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customer) {
        Customer customer1 = new Customer();
        customer1.setFName(customer.getFName());
        customer1.setLName(customer.getLName());
        customer1.setEmail(customer.getEmail());
        return mapToResponse(customerRepository.save(customer1));
    }

    private CustomerResponseDTO mapToResponse(Customer customer){
        return new CustomerResponseDTO(customer.getId(), customer.getFName(), customer.getLName(), customer.getOrderList().stream().map((order)->mapToOrderResponse(order)).toList());
    }

    private OrderSummaryDTO mapToOrderResponse(Order order) {
        return new OrderSummaryDTO(order.getOrder_id(), order.getOrderDate());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public CustomerResponseDTO findById(Long id) {
        return customerRepository.findById(id).map((this::mapToResponse)).orElseThrow(()->new CustomerNotFound("no customer found..."));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerResponseDTO> findAllCustomers() {
        return customerRepository.findAll().stream().map((customer)->mapToResponse(customer)).toList();
    }

    @Override
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public void updateById(Long id, CustomerRequestDTO customer) {
        Customer customer1 = customerRepository.findById(id).orElseThrow(()->new CustomerNotFound("no customer found.."));
        customer1.setFName(customer.getFName());
        customer1.setLName(customer.getLName());
        customer1.setEmail(customer.getEmail());
        customerRepository.save(customer1);
    }

    @Override
    @PreAuthorize(" #id == authentication.principal.id or hasRole('ADMIN')")
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }
}
