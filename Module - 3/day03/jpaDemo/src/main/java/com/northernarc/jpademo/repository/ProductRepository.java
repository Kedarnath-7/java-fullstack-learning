package com.northernarc.jpademo.repository;

import com.northernarc.jpademo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
