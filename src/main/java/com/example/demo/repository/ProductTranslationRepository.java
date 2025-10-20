package com.example.demo.repository;

import com.example.demo.entity.ProductTranslation;
import com.example.demo.entity.ProductTranslationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductTranslationRepository extends JpaRepository<ProductTranslation, ProductTranslationId> {
    
    @Query("SELECT pt FROM ProductTranslation pt WHERE pt.id.languageId = :languageId")
    List<ProductTranslation> findByLanguageId(@Param("languageId") String languageId);
}
