package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producttranslation")
public class ProductTranslation {

    @EmbeddedId
    private ProductTranslationId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "ProductID")
    private Product product;

    @Column(name = "ProductName")
    private String productName;

    // ⚠️ Sửa lại tên cột cho đúng trong DB
    @Column(name = "ProductDescription")
    private String description;

    // Getter & Setter
    public ProductTranslationId getId() { return id; }
    public void setId(ProductTranslationId id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
