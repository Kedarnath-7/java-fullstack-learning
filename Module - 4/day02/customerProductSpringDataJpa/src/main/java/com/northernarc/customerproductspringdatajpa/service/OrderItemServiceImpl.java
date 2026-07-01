package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.exceptions.OrderItemNotFound;
import com.northernarc.customerproductspringdatajpa.model.OrderItem;
import com.northernarc.customerproductspringdatajpa.repository.OrderItemRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepository orderItemRepository;
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public OrderItem addOrderItem(OrderItem orderItem) {
        // In real scenario, verify the order belongs to the authenticated user
        return orderItemRepository.save(orderItem);
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteOrderItemById(Long id) {
        // In real scenario, verify the order item belongs to user's order
        orderItemRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public OrderItem findById(Long id) {
        // In real scenario, verify the order item belongs to user's order
        return orderItemRepository.findById(id).orElseThrow(()->new OrderItemNotFound("no order item found...."));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderItem> findAllOrderItems() {
        // Only admins should see all order items across all orders
        return orderItemRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrderItems() {
        // Only admins can delete all order items
        orderItemRepository.deleteAll();
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateById(Long id, OrderItem orderItem) {
        // In real scenario, verify the order item belongs to user's order
        OrderItem orderItem1 = orderItemRepository.findById(id).orElseThrow(()->new OrderItemNotFound("no order item not found..."));
        orderItem1.setProduct(orderItem.getProduct());
        orderItem1.setQuantity(orderItem.getQuantity());
        orderItem1.setOrder(orderItem.getOrder());
        orderItemRepository.save(orderItem1);
    }
}
