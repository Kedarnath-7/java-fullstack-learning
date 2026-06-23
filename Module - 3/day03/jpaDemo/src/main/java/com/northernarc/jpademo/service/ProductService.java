package com.northernarc.jpademo.service;

import com.northernarc.jpademo.model.Product;

import java.util.Collection;

public interface ProductService {
    Product addProduct(Product product);
    Product get(int id);
    void delete(int id);
    void update(int id, Product product);
    Collection<Product> getAll();
}
