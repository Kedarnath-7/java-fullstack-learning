package com.northernarc.jdbcdemo.dao;

import com.northernArc.restapiDemo.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductDaoImpl implements ProductDao{

    private Map<Integer, Product> products;

    @PostConstruct
    public void init(){
        System.out.println("Initializing products data....");
        products = new HashMap<>();
        products.put(1, new Product(1, "Laptop", "Dell","Electronics"));
        products.put(2, new Product(2, "Shoes", "US Polo Ass","Clothing"));
        products.put(3, new Product(3, "Bottle", "Milton","Essentials"));
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destroying products data....");
        products.clear();
    }

    @Override
    public Product addProduct(Product product) {
        return products.put(product.getId(), product);
    }

    @Override
    public Product getProductById(int id) {
        return products.get(id);
    }

    @Override
    public Collection<Product> getAllProducts() {
        return products.values();
    }

    @Override
    public void updateProductById(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void deleteProductById(int id) {
        products.remove(id);
    }

    @Override
    public void deleteAllProducts() {
        products.clear();
    }
}
