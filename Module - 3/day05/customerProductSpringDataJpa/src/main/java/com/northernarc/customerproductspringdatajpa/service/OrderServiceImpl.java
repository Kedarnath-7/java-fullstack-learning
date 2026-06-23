package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.dto.OrderItemSummaryDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderResponseDTO;
import com.northernarc.customerproductspringdatajpa.exceptions.CustomerNotFound;
import com.northernarc.customerproductspringdatajpa.exceptions.OrderNotFound;
import com.northernarc.customerproductspringdatajpa.model.Order;
import com.northernarc.customerproductspringdatajpa.repository.CustomerRepository;
import com.northernarc.customerproductspringdatajpa.repository.OrderRepository;
import com.northernarc.customerproductspringdatajpa.repository.ProductRepository;
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
    public OrderResponseDTO addOrder(OrderRequestDTO order) {
        Order order1 = new Order();
        order1.setOrderDate(order.getOrderDate());
        order1.setCustomer(customerRepository.findById(order.getCustomerId()).orElseThrow(()-> new CustomerNotFound("no customer found...")));
        order1.setOrderItemList(order.getOrderItems());
        return mapToResponse(orderRepository.save(order1));
    }

    private OrderResponseDTO mapToResponse(Order order){
        return new OrderResponseDTO(order.getOrder_id(), order.getOrderDate(), order.getCustomer()., order.getOrderItemList().stream().map((orderItem)-> new OrderItemSummaryDTO(orderItem.getId(), orderItem.getQuantity())).toList());
    }

    @Override
    public OrderResponseDTO findById(Long id) {
        return orderRepository.findById(id).map((order)->mapToResponse(order)).orElseThrow(()->new OrderNotFound("no order found..."));
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, OrderRequestDTO order) {
        Order order1 = orderRepository.findById(id).orElseThrow(()->new OrderNotFound("no order found..."));
        order1.setOrderDate(order.getOrderDate());
        order1.setOrderItemList(order.getOrderItems().stream().map());
        order1.setCustomer(customerRepository.findById(order.getCustomerId()).orElseThrow(()->new CustomerNotFound("no customer found...")));
        orderRepository.save(order1);
    }

    @Override
    public List<OrderResponseDTO> findAllOrders() {
        return orderRepository.findAll().stream().map((order)->mapToResponse(order)).toList();
    }
}
