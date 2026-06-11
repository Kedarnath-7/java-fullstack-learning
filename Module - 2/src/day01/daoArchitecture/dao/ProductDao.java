package day01.daoArchitecture.dao;

import day01.daoArchitecture.entity.Product;

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
