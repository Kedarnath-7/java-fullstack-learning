package com.northernarc.customerproductspringdatajpa.controller;

import com.northernarc.customerproductspringdatajpa.model.OrderItem;
import com.northernarc.customerproductspringdatajpa.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItem> add(@RequestBody OrderItem item) {
        return new ResponseEntity<>(orderItemService.addOrderItem(item), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAll() {
        return ResponseEntity.ok(orderItemService.findAllOrderItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItem item) {
        orderItemService.updateById(id, item);
        return ResponseEntity.ok(orderItemService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.deleteOrderItemById(id);
        return ResponseEntity.noContent().build();
    }
}