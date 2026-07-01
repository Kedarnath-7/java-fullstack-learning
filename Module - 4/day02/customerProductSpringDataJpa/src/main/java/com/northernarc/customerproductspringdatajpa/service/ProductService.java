package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product findById(Long id);
    List<Product> findAllProducts();
    void deleteById(Long id);
    void deleteAllProducts();
    void updateById(Long id, Product product);
    List<Product> searchProducts(String name, String brand, String category);
}
