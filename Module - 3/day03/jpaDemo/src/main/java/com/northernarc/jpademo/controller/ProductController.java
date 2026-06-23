package com.northernarc.jpademo.controller;
import com.northernarc.jpademo.model.Product;
import com.northernarc.jpademo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<Product> getAllProducts(){
        return new ResponseEntity(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.noContent().build();
    }
}
