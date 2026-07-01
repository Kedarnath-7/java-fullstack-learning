package com.northernarc.customerproductspringdatajpa.repository;

import com.northernarc.customerproductspringdatajpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
