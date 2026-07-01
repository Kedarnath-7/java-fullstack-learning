package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.dto.CustomerSummaryDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderItemSummaryDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderResponseDTO;
import com.northernarc.customerproductspringdatajpa.exceptions.CustomerNotFound;
import com.northernarc.customerproductspringdatajpa.exceptions.OrderNotFound;
import com.northernarc.customerproductspringdatajpa.model.Customer;
import com.northernarc.customerproductspringdatajpa.model.Order;
import com.northernarc.customerproductspringdatajpa.repository.CustomerRepository;
import com.northernarc.customerproductspringdatajpa.repository.OrderRepository;
import com.northernarc.customerproductspringdatajpa.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public OrderResponseDTO addOrder(OrderRequestDTO order) {
        Order order1 = new Order();
        order1.setOrderDate(order.getOrderDate());
        order1.setCustomer(customerRepository.findById(order.getCustomerId()).orElseThrow(()-> new CustomerNotFound("no customer found...")));
        //order1.setOrderItemList(order.getOrderItems());
        return mapToResponse(orderRepository.save(order1));
    }

    private OrderResponseDTO mapToResponse(Order order){
        return new OrderResponseDTO(order.getOrder_id(), order.getOrderDate(), mapToCustomerSummary(order.getCustomer()), order.getOrderItemList().stream().map((orderItem)-> new OrderItemSummaryDTO(orderItem.getId(), orderItem.getQuantity())).toList());
    }

    private CustomerSummaryDTO mapToCustomerSummary(Customer customer){
        return new CustomerSummaryDTO(customer.getId(), customer.getFName(), customer.getLName());
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFound("no order found..."));
        
        // Manual ownership check: verify order belongs to authenticated user
        // In real scenario, you'd compare with authentication.principal.customerId
        // For now, allowing all authenticated users (add ownership logic based on your User entity)
        
        return mapToResponse(order);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteById(Long id) {
        // Manual ownership check should be added here
        // Verify order belongs to authenticated user before deleting
        orderRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAllOrders() {
        // Only admins can delete all orders
        orderRepository.deleteAll();
    }


    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateById(Long id, OrderRequestDTO order) {
        Order order1 = orderRepository.findById(id).orElseThrow(()->new OrderNotFound("no order found..."));
        
        // Manual ownership check should be added here
        // Verify order belongs to authenticated user before updating
        
        order1.setOrderDate(order.getOrderDate());
        //order1.setOrderItemList(order.getOrderItems().stream().map());
        // order1.setCustomer(customerRepository.findById(order.getCustomerId()).orElseThrow(()->new CustomerNotFound("no customer found...")));
        orderRepository.save(order1);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponseDTO> findAllOrders() {
        // Only admins should see all orders
        // For users, create a separate method like findMyOrders(Long customerId)
        return orderRepository.findAll().stream().map((order)->mapToResponse(order)).toList();
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<OrderResponseDTO> findMyOrders(Long customerId) {
        // Verify customerId matches authenticated user
        return orderRepository.findById(customerId).stream().map((order)->mapToResponse(order)).toList();
    }

}
