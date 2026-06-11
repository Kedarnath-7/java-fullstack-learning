package day02.streams.ui;

import day02.streams.dao.ProductDao;
import day02.streams.dao.ProductDaoImpl;
import day02.streams.entity.Product;

public class ProductMain {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDaoImpl();
        productDao.save(new Product(20, "Milk", "Jersey", "Food"));
        productDao.save(new Product(30, "Soap", "Santoor", "Cosmetics"));
        productDao.save(new Product(40, "Brush", "Colgate", "Cosmetics"));
        productDao.save(new Product(50, "Pen", "Reynolds", "Stationary"));
        productDao.save(new Product(25, "Socks", "Adidas", "Clothing"));

        for(Product p : productDao.findAll()){
            System.out.println(p);
        }

        System.out.println("==============");

        productDao.updateById(new Product(25, "Shoes", "Adidas", "Clothing"));

        for(Product p : productDao.findAll()){
            System.out.println(p);
        }

        System.out.println("==============");

        productDao.deleteById(15);
        for(Product p : productDao.findAll()){
            System.out.println(p);
        }

        System.out.println("====================");

        System.out.println("Find by brand: ");
        System.out.println(productDao.findByBrand("adidas"));
        System.out.println("=====================");
        System.out.println("Find by name: ");
        System.out.println(productDao.findByName("Milk"));
        System.out.println("=====================");
        System.out.println("Find by category: ");
        System.out.println(productDao.findByCategory("cosmetics"));
    }
}
