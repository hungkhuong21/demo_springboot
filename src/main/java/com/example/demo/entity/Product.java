package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "ProductID")
    private int productId;

    @Column(name = "Price")
    private double price;

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
