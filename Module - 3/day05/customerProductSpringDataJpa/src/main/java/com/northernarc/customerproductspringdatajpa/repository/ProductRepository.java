package com.northernarc.customerproductspringdatajpa.repository;

import com.northernarc.customerproductspringdatajpa.dto.ProductResponseDTO;
import com.northernarc.customerproductspringdatajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductResponseDTO> findByNameContainingIgnoreCase(String name);

    List<ProductResponseDTO> findByBrandContainingIgnoreCase(String brand);

    List<ProductResponseDTO> findByCategoryContainingIgnoreCase(String category);
}
