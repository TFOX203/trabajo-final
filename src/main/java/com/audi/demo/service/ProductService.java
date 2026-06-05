package com.audi.demo.service;

import java.util.List;
import com.audi.demo.domain.Product;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(long id);

    Product saveProduct(Product product);

    void deleteProduct(long id);
}