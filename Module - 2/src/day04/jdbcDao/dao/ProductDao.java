package day04.jdbcDao.dao;

import day04.jdbcDao.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface ProductDao {
    public int saveProduct(Product product);
    public Product findProductById(int id);
    public void updateProductById(int id, Product book);
    public void deleteProductById(int id);
    public Collection<Product> findAllProducts();
    public Collection<Product> findByName(String name);
    public Collection<Product> findByBrand(String brand);
    public Collection<Product> findByCategory(String category);
    public Product mapToProduct(ResultSet rs) throws SQLException;
    public void deleteAllProducts();
    public Collection<Product> sortProductsByNameAsc();
    public Collection<Product> sortProductsByNameDesc();
    public Collection<Product> sortProductsByBrandAsc();
    public Collection<Product> sortProductsByBrandDesc();
    public Collection<Product> sortProductsByCategoryAsc();
    public Collection<Product> sortProductsByCategoryDesc();
}
