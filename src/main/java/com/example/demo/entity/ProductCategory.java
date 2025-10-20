package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "productcategory")
public class ProductCategory {
    @Id
    @Column(name = "ProductCategoryID")
    private int productCategoryID;

    @Column(name = "CanBeShipped")
    private boolean canBeShipped;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    private List<Product> products;

    // Getters và Setters
    public int getProductCategoryID() { return productCategoryID; }
    public void setProductCategoryID(int productCategoryID) { this.productCategoryID = productCategoryID; }

    public boolean isCanBeShipped() { return canBeShipped; }
    public void setCanBeShipped(boolean canBeShipped) { this.canBeShipped = canBeShipped; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}
