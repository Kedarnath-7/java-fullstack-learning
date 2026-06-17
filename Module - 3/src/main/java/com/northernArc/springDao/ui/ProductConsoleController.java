package com.northernArc.springDao.ui;

import com.northernArc.springDao.dao.ProductDao;
import com.northernArc.springDao.entity.Product;

import java.util.Scanner;

public class ProductConsoleController {
    private Scanner scanner;
    private ProductDao productDao;

    public ProductConsoleController(Scanner scanner, ProductDao productDao){
        this.scanner = scanner;
        this.productDao = productDao;
    }

    public void showWelcomeMessage(){
        System.out.println("Welcome to the Book Console Application....");
        System.out.println("Explore wide range of operations you can perform...");
    }

    public void showMenu(){
        System.out.println("1. Add product");
        System.out.println("2. View all products");
        System.out.println("3. Update product");
        System.out.println("4. Delete product");
        System.out.println("5. Find by id");
        System.out.println("6. Find by name");
        System.out.println("7. Find by brand");
        System.out.println("8. Sort by title asc");
        System.out.println("9. Sort by title desc");
        System.out.println("10. Sort by brand asc");
        System.out.println("11. Sort by brand desc");
        System.out.println("12. Sort by category asc");
        System.out.println("13. Sort by category desc");
        System.out.println("14. Delete all products");
        System.out.println("15. Find by category");
        int choice = scanner.nextInt();
        redirectChoice(choice);
    }

    private void redirectChoice(int choice){
        switch (choice){
            case 1:
                add();
                break;
            case 2:
                findAll();
                break;
            case 3:
                updateById();
                break;
            case 4:
                deleteById();
                break;
            case 5:
                findById();
                break;
            case 6:
                findByName();
                break;
            case 7:
                findByBrand();
                break;
            case 8:
                productDao.sortProductsByNameAsc();
                break;
            case 9:
                productDao.sortProductsByNameDesc();
                break;
            case 10:
                productDao.sortProductsByBrandAsc();
                break;
            case 11:
                productDao.sortProductsByBrandDesc();
                break;
            case 12:
                productDao.sortProductsByCategoryAsc();
                break;
            case 13:
                productDao.sortProductsByCategoryDesc();
                break;
            case 14:
                findByCategory();
                break;
            default:
                System.out.println("Invalid choice...");

        }
    }

    private void add(){
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter category: ");
        String category = scanner.nextLine();
        productDao.saveProduct(new Product(name, brand,category));
    }

    private void updateById(){
        System.out.println("Enter book id: ");
        int id = scanner.nextInt();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter category: ");
        String category = scanner.nextLine();
        productDao.updateProductById(id, new Product(name, brand,category));
    }

    private void deleteById(){
        System.out.println("Enter product id: ");
        int id = scanner.nextInt();
        productDao.deleteProductById(id);
    }
    private void findById(){
        System.out.println("Enter product id: ");
        int id = scanner.nextInt();
        productDao.findProductById(id);
    }
    private void findAll(){
        productDao.findAllProducts();
    }

    private void findByName(){
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        productDao.findByName(name);
    }
    private void findByBrand(){
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        productDao.findByBrand(brand);
    }
    private void findByCategory(){
        System.out.println("Enter category: ");
        String category = scanner.nextLine();
        productDao.findByCategory(category);
    }

}
