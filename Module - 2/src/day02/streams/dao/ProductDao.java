package day02.streams.dao;

import day02.streams.entity.Product;

public interface ProductDao {
    public Iterable<Product> findByName(String name);
    public Iterable<Product> findByBrand(String brand);
    public Iterable<Product> findByCategory(String category);
    public void save(Product product);
    public void updateById(Product product);
    public void deleteById(int id);
    public Iterable<Product> findAll();
    public void deleteAll();

}
