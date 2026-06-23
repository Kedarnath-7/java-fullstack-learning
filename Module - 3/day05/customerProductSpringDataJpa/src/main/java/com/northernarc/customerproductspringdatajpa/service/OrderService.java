package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.dto.OrderRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderResponseDTO;
import com.northernarc.customerproductspringdatajpa.model.Order;

import java.util.List;

public interface OrderService {
    //Order addOrder(Order order);
    OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO findById(Long id);
    void deleteById(Long id);
    void deleteAllOrders();
    void updateById(Long id, OrderRequestDTO order);
    List<OrderResponseDTO> findAllOrders();

}
