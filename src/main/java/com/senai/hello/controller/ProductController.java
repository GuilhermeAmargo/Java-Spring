package com.senai.hello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import com.senai.hello.entity.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "Product 1", 10.0));
        products.add(new Product(2L, "Product 2", 20.0));
        products.add(new Product(3L, "Product 3", 30.0));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}") 
    public Product getProductById(@PathVariable("id") Long id) {
        return products.stream() 
                       .filter(product -> product.getId().equals(id))
                       .findFirst()
                       .orElseThrow(() -> new ResponseStatusException(
                       HttpStatus.NOT_FOUND, 
                       "Produto com ID " + id + " não encontrado."
                   ));
    }
}
