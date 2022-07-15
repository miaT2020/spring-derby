package com.example.demo.springandreact.service;

import java.util.Optional;

import com.example.demo.springandreact.domain.Product;

public interface ProductService {
    Iterable<Product> list();
    
    Product create(Product product);
    
    Optional<Product> read(long id);
    
    Product update(long id, Product product);
    
    void delete(long id);
}