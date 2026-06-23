package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.exceptions.ProductNotFound;
import com.northernarc.customerproductspringdatajpa.model.Product;
import com.northernarc.customerproductspringdatajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFound("no product found..."));
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public void updateById(Long id, Product product) {
        Product product1 = productRepository.findById(id).orElseThrow(()->new ProductNotFound("no product found..."));
        product1.setName(product.getName());
        product1.setBrand(product.getBrand());
        product1.setCategory(product.getCategory());
        product1.setCost(product.getCost());
        product1.setStock(product.getStock());
        productRepository.save(product1);
    }

    @Override
    public List<Product> searchProducts(String name, String brand, String category) {

        if (name != null) {
            return productRepository.findByNameContainingIgnoreCase(name);
        }

        if (brand != null) {
            return productRepository.findByBrandContainingIgnoreCase(brand);
        }

        if (category != null) {
            return productRepository.findByCategoryContainingIgnoreCase(category);
        }

        return productRepository.findAll();
    }
}
