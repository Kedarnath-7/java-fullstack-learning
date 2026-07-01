package com.northernarc.customerproductspringdatajpa.repository;

import com.northernarc.customerproductspringdatajpa.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
