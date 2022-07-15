package com.example.demo.springandreact.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.springandreact.domain.Product;
import com.example.demo.springandreact.repository.ProductRepository;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    
    @Autowired
    public ProductServiceImpl(ProductRepository postRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }

    @Override
    public  Optional<Product> read(long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product create(Product product) {
        // save the new product
        return productRepository.save(product);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(long id, Product update) {
        Optional<Product> product = productRepository.findById(id);
        if( update.getProductName() != null ) {
        	if (product.isPresent()) {
        		product.get().setProductName(update.getProductName());
        	}
        }
        return productRepository.save(product.get());
    }
}