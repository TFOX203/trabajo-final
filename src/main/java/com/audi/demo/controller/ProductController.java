package com.audi.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audi.demo.domain.Product;
import com.audi.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable long id) {
        return service.getProduct(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.deleteProduct(id);
    }
}