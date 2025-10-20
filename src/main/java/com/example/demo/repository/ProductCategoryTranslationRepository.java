package com.example.demo.repository;

import com.example.demo.entity.ProductCategoryTranslation;
import com.example.demo.entity.ProductCategoryTranslationId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductCategoryTranslationRepository extends JpaRepository<ProductCategoryTranslation, ProductCategoryTranslationId> {
    // tìm theo language id (trong EmbeddedId)
    List<ProductCategoryTranslation> findById_LanguageID(String languageID);

    // hoặc nếu bạn muốn theo productCategory id:
    // List<ProductCategoryTranslation> findById_ProductCategoryID(Long productCategoryID);
}
