package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductTranslationId implements Serializable {
    private int productId;
    private String languageId;

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getLanguageId() { return languageId; }
    public void setLanguageId(String languageId) { this.languageId = languageId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductTranslationId)) return false;
        ProductTranslationId that = (ProductTranslationId) o;
        return productId == that.productId && Objects.equals(languageId, that.languageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, languageId);
    }
}
