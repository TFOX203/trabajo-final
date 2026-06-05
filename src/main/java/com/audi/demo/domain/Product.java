package com.audi.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String brand;
    private String tag;

    public Product() {
    }

    // getters y setters
}