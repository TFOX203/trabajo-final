package com.audi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.audi.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}