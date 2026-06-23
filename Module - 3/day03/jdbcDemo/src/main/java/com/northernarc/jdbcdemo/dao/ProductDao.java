package com.northernarc.jdbcdemo.dao;

import com.northernArc.restapiDemo.model.Product;

import java.util.Collection;

public interface ProductDao {
    Product addProduct(Product product);
    Product getProductById(int id);
    Collection<Product> getAllProducts();
    void updateProductById(int id, Product product);
    void deleteProductById(int id);
    void deleteAllProducts();

}
