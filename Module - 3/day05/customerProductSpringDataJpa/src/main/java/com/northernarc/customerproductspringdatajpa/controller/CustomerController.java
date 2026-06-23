package com.northernarc.customerproductspringdatajpa.controller;

import com.northernarc.customerproductspringdatajpa.model.Customer;
import com.northernarc.customerproductspringdatajpa.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id,
                                           @RequestBody Customer customer) {
        customerService.updateById(id, customer);
        return ResponseEntity.ok(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}