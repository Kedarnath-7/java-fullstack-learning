package day01.daoArchitecture.dao;

import day01.daoArchitecture.entity.Product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final List<Product> products = new LinkedList<Product>();
    @Override
    public Iterable<Product> findByName(String name) {
        List<Product> result = new LinkedList<Product>();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {
        List<Product> result = new LinkedList<>();
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public Iterable<Product> findByCategory(String category) {
        List<Product> result = new LinkedList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void updateById(Product product) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                p.setName(product.getName());
                p.setBrand(product.getBrand());
                p.setCategory(product.getCategory());
            }
        }
    }

    @Override
    public void deleteById(int id) {
        Iterator<Product> itr = products.iterator();
        while(itr.hasNext()){
            Product p = itr.next();
            if(p.getId() == id){
                itr.remove();
                return;
            }
        }
    }

    @Override
    public Iterable<Product> findAll() {
        return products;
    }

    @Override
    public void deleteAll() {
        products.clear();
    }
}
