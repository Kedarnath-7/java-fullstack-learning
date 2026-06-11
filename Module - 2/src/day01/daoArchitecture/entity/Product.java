package day01.daoArchitecture.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private String brand;
    private String category;
    private List<Product> products;

    public Product(int id, String name, String brand, String category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.products = new LinkedList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return "{Name: " + this.name + ", Brand: " + this.brand + ", Category: " + this.category+"}";
    }

}
