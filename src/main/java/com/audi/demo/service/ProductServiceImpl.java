package com.audi.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.audi.demo.domain.Product;
import com.audi.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        repository.deleteById(id);
    }
}