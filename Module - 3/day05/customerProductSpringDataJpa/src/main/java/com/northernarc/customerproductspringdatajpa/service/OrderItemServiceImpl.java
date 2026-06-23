package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.exceptions.OrderItemNotFound;
import com.northernarc.customerproductspringdatajpa.model.OrderItem;
import com.northernarc.customerproductspringdatajpa.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepository orderItemRepository;
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(()->new OrderItemNotFound("no order item found...."));
    }

    @Override
    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public void deleteAllOrderItems() {
        orderItemRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, OrderItem orderItem) {
        OrderItem orderItem1 = orderItemRepository.findById(id).orElseThrow(()->new OrderItemNotFound("no order item not found..."));
        orderItem1.setProduct(orderItem.getProduct());
        orderItem1.setQuantity(orderItem.getQuantity());
        orderItem1.setOrder(orderItem.getOrder());
        orderItemRepository.save(orderItem1);
    }
}
