package day05.collectionDemo;

public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private String brand;
    private double discount;
    private double rating;

    public Product(int id, String name, String category, double price, String brand, double discount, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.brand = brand;
        this.discount = discount;
        this.rating = rating;

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return name + " " + brand + " " + price + " " + category;
    }

    @Override
    public boolean equals(Object o) {
        Product p = (Product)o;
        return this.id == p.id;
    }
    @Override
    public int hashCode() {
        return id;
    }
}
