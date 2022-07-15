package com.example.demo.springandreact.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
  //  @NotBlank
    private String productName;
    private double price;
    
    @SuppressWarnings("unused")
    public Product(){}
    
    public Product(String productname) {
        this.productName = productname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productname) {
        this.productName = productname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   
    @Override
    public String toString() {
        return "Product [productName=" + productName + ", price=" + price + "]";
    }
	   
}