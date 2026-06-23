package com.northernarc.jpademo.service;
import com.northernarc.jpademo.model.Product;
import com.northernarc.jpademo.repository.ProductRepository;

import java.util.Collection;

public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void update(int id, Product product) {
        Product product1 = productRepository.findById(id).get();
        product1.setName(product1.getName());
        product1.setBrand(product.getBrand());
        product1.setCategory(product.getCategory());
        productRepository.save(product1);
    }

    @Override
    public Collection<Product> getAll() {
        return productRepository.findAll();
    }
}
