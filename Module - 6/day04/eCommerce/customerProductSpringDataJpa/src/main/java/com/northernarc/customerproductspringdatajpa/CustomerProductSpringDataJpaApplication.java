package com.northernarc.customerproductspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CustomerProductSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerProductSpringDataJpaApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner seedData(
//            CustomerRepository customerRepository,
//            ProductRepository productRepository,
//            OrderRepository orderRepository,
//            OrderItemRepository orderItemRepository,
//            PasswordEncoder passwordEncoder
//    ) {
//        return args -> {
//            if (customerRepository.count() == 0) {
//                Customer admin = new Customer();
//                admin.setFName("Admin");
//                admin.setLName("User");
//                admin.setEmail("admin@northernarc.com");
//                admin.setPassword(passwordEncoder.encode("admin123"));
//                admin.setRole(CustomerRole.ADMIN);
//
//                Customer user = new Customer();
//                user.setFName("John");
//                user.setLName("Doe");
//                user.setEmail("john.doe@northernarc.com");
//                user.setPassword(passwordEncoder.encode("password123"));
//                user.setRole(CustomerRole.USER);
//
//                customerRepository.saveAll(List.of(admin, user));
//            }
//
//            if (productRepository.count() == 0) {
//                Product laptop = new Product();
//                laptop.setName("ThinkPad E14");
//                laptop.setBrand("Lenovo");
//                laptop.setCategory("Electronics");
//                laptop.setCost(new BigDecimal("69999.00"));
//                laptop.setStock(20);
//
//                Product phone = new Product();
//                phone.setName("Galaxy S24");
//                phone.setBrand("Samsung");
//                phone.setCategory("Electronics");
//                phone.setCost(new BigDecimal("74999.00"));
//                phone.setStock(30);
//
//                Product chair = new Product();
//                chair.setName("Ergo Chair");
//                chair.setBrand("Featherlite");
//                chair.setCategory("Furniture");
//                chair.setCost(new BigDecimal("15999.00"));
//                chair.setStock(15);
//
//                productRepository.saveAll(List.of(laptop, phone, chair));
//            }
//
//            if (orderRepository.count() == 0) {
//                Customer orderCustomer = customerRepository.findByEmailIgnoreCase("john.doe@northernarc.com")
//                        .orElseGet(() -> customerRepository.findAll().getFirst());
//
//                Order order = new Order();
//                order.setCustomer(orderCustomer);
//                order.setOrderDate(LocalDate.now().minusDays(1));
//                orderRepository.save(order);
//            }
//
//            if (orderItemRepository.count() == 0) {
//                Order order = orderRepository.findAll().getFirst();
//                List<Product> products = productRepository.findAll();
//                Product firstProduct = products.getFirst();
//                Product secondProduct = products.size() > 1 ? products.get(1) : firstProduct;
//
//                OrderItem item1 = new OrderItem();
//                item1.setOrder(order);
//                item1.setProduct(firstProduct);
//                item1.setQuantity(1);
//
//                OrderItem item2 = new OrderItem();
//                item2.setOrder(order);
//                item2.setProduct(secondProduct);
//                item2.setQuantity(2);
//
//                orderItemRepository.saveAll(List.of(item1, item2));
//            }
//        };
//    }

}
