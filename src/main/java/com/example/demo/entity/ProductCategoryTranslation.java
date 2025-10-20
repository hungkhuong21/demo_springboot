package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productcategorytranslation")
public class ProductCategoryTranslation {

    @EmbeddedId
    private ProductCategoryTranslationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productCategoryID") // phải khớp với field name trong ProductCategoryTranslationId
    @JoinColumn(name = "ProductCategoryID")
    private ProductCategory productCategory;

    @Column(name = "CategoryName")
    private String categoryName;

    public ProductCategoryTranslationId getId() {
        return id;
    }

    public void setId(ProductCategoryTranslationId id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
