package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.model.Order;
import com.northernarc.customerproductspringdatajpa.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem addOrderItem(OrderItem orderItem);
    void deleteOrderItemById(Long id);
    OrderItem findById(Long id);
    List<OrderItem> findAllOrderItems();
    void deleteAllOrderItems();
    void updateById(Long id, OrderItem orderItem);

}
