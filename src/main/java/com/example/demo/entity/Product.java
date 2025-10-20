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

    @Column(name = "ProductCategoryID")
    private int productCategoryID;

    @ManyToOne
    @JoinColumn(name = "ProductCategoryID", insertable = false, updatable = false)
    private ProductCategory productCategory;

    // Getters và Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getProductCategoryID() { return productCategoryID; }
    public void setProductCategoryID(int productCategoryID) { this.productCategoryID = productCategoryID; }

    public ProductCategory getProductCategory() { return productCategory; }
    public void setProductCategory(ProductCategory productCategory) { this.productCategory = productCategory; }
}
