package day05.arrayDemo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ProductMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the product sorting program....");
        System.out.println("Enter the size of the products array: ");
        Scanner sc = new Scanner(System.in);
        int arrSize = sc.nextInt();
        sc.nextLine();
        Product[] products = new Product[arrSize];
        System.out.println("Enter the " + arrSize + " product details as prompted....");

        for (int i = 0; i < arrSize; i++) {
            System.out.println("Enter the product "+ (int)(i+1) + " details as prompted....");
            System.out.println("Enter the id of the product "+ (int)(i+1) +": ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the name of the product "+ (int)(i+1) +": ");
            String name = sc.nextLine();
            System.out.println("Enter the brand of the product "+ (int)(i+1) +": ");
            String brand = sc.nextLine();
            System.out.println("Enter the price of the product "+ (int)(i+1) +": ");
            double price = sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter the category of the product "+ (int)(i+1) +": ");
            String category = sc.nextLine();
            System.out.println("Enter the discount of the product "+ (int)(i+1) +": ");
            double discount = sc.nextDouble();
            System.out.println("Enter the rating of the product "+ (int)(i+1) +": ");
            double rating = sc.nextDouble();
            sc.nextLine();
            products[i] = new Product(id, name, category, price, brand, discount, rating);
        }

        System.out.println("Products array before sorting....");
        System.out.println(Arrays.toString(products));

        boolean flag = true;
        while(flag){

            System.out.println("Choose how you want to sort: 1. Name Asc\n2. Name Desc\n3. Price Asc\n4. Price Desc\n5. Discount Asc\n6. Discount Dsc\n7. Brand Asc\n8. Brand Desc\n9. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println("Products array after sorting.....");
            switch (choice) {
                case 1:
                    Arrays.sort(products, new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    break;
                case 2:
                    Arrays.sort(products, new  Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return o2.getName().compareTo(o1.getName());
                        }
                    });
                    break;
                case 3:
                    Arrays.sort(products, new Comparator<Product>(){
                        @Override
                        public int compare(Product o1, Product o2){
                          return Double.compare(o1.getPrice(), o2.getPrice());
                        }
                    });
                    break;
                case 4:
                    Arrays.sort(products, new Comparator<Product>(){
                        @Override
                        public int compare(Product o1, Product o2){
                            return Double.compare(o2.getPrice(), o1.getPrice());
                        }
                    });
                    break;
                case 5:
                    Arrays.sort(products, new Comparator<Product>(){
                        @Override
                        public int compare(Product o1, Product o2){
                            return Double.compare(o1.getDiscount(), o2.getDiscount());
                        }
                    });
                    break;
                case 6:
                    Arrays.sort(products, new  Comparator<Product>(){
                        @Override
                        public int compare(Product o1, Product o2){
                            return Double.compare(o2.getDiscount(), o1.getDiscount());
                        }
                    });
                    break;
                case 7:
                    Arrays.sort(products, new  Comparator<Product>(){
                        @Override
                        public int compare(Product o1, Product o2){
                            if(o1.getBrand().equalsIgnoreCase(o2.getBrand())){
                                return o1.getName().compareTo(o2.getName());
                            }
                            return o1.getBrand().compareTo(o2.getBrand());
                        }
                    });
                    break;
                case 8:
                    Arrays.sort(products, new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            if(o1.getBrand().equalsIgnoreCase(o2.getBrand())){
                                return o2.getName().compareTo(o1.getName());
                            }
                            return  o2.getBrand().compareTo(o1.getBrand());
                        }
                    });
                    break;
                case 9:
                    System.out.println("Thanks for using our program...exiting....");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice...try again...");
                    break;
            }
            System.out.println(Arrays.toString(products));
        }

    }
}
