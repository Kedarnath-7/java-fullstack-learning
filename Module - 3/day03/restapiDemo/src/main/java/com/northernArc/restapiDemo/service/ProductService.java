package com.northernArc.restapiDemo.service;

import com.northernArc.restapiDemo.model.Product;

import java.util.Collection;

public interface ProductService {
    Product save(Product product);
    void updateById(int id, Product product);
    void deleteById(int id);
    Collection<Product> findAll();
    Product findById(int id);
}
