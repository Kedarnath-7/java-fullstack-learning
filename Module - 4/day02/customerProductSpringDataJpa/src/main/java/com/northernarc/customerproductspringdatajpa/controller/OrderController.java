package com.northernarc.customerproductspringdatajpa.controller;

import com.northernarc.customerproductspringdatajpa.dto.OrderRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderResponseDTO;
import com.northernarc.customerproductspringdatajpa.model.Order;
import com.northernarc.customerproductspringdatajpa.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO request) {
        return new ResponseEntity<>(orderService.addOrder(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAll() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id,
                                        @RequestBody OrderRequestDTO order) {
        orderService.updateById(id, order);
        return ResponseEntity.ok(orderService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        //Order order = orderService.findById(id);
        orderService.deleteById(id);

        return ResponseEntity.ok("Order cancelled successfully");
    }
}
