package com.northernarc.customerproductspringdatajpa.repository;

import com.northernarc.customerproductspringdatajpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers();

    Optional<Customer> findByEmailIgnoreCase(String email);
}
