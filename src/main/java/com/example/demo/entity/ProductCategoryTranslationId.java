package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductCategoryTranslationId implements Serializable {

    @Column(name = "ProductCategoryID")
    private Long productCategoryID;

    @Column(name = "LanguageID")
    private String languageID;

    public ProductCategoryTranslationId() {}

    public ProductCategoryTranslationId(Long productCategoryID, String languageID) {
        this.productCategoryID = productCategoryID;
        this.languageID = languageID;
    }

    public Long getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(Long productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getLanguageID() {
        return languageID;
    }

    public void setLanguageID(String languageID) {
        this.languageID = languageID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategoryTranslationId)) return false;
        ProductCategoryTranslationId that = (ProductCategoryTranslationId) o;
        return Objects.equals(productCategoryID, that.productCategoryID)
                && Objects.equals(languageID, that.languageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCategoryID, languageID);
    }
}
