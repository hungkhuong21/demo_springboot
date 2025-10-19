package com.example.demo.repository;

import com.example.demo.entity.ProductTranslation;
import com.example.demo.entity.ProductTranslationId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductTranslationRepository extends JpaRepository<ProductTranslation, ProductTranslationId> {
    List<ProductTranslation> findByIdLanguageId(String languageId);
}
