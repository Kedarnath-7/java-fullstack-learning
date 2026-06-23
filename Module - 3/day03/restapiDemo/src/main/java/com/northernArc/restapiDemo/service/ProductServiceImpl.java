package com.northernArc.restapiDemo.service;

import com.northernArc.restapiDemo.dao.ProductDao;
import com.northernArc.restapiDemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product save(Product product) {
        // validation logic
        return productDao.addProduct(product);
    }

    @Override
    public void updateById(int id, Product product) {
        // validation logic
        productDao.updateProductById(id, product);
    }

    @Override
    public void deleteById(int id) {
        // validation logic
        productDao.deleteProductById(id);
    }

    @Override
    public Collection<Product> findAll() {
        // validation logic
        return productDao.getAllProducts();
    }

    @Override
    public Product findById(int id) {
        // validation logic
        return productDao.getProductById(id);
    }
}
