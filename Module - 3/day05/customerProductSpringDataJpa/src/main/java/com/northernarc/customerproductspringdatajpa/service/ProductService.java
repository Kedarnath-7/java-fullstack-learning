package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.dto.ProductRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.ProductResponseDTO;
import com.northernarc.customerproductspringdatajpa.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO product);
    ProductResponseDTO findById(Long id);
    List<ProductResponseDTO> findAllProducts();
    void deleteById(Long id);
    void deleteAllProducts();
    void updateById(Long id, ProductRequestDTO product);
    List<ProductResponseDTO> searchProducts(String name, String brand, String category);
}
