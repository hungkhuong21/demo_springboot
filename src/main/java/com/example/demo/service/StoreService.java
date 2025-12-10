package com.example.demo.service;

import com.example.demo.entity.Language;
import com.example.demo.entity.ProductCategoryTranslation;
import com.example.demo.entity.ProductTranslation;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.repository.ProductCategoryTranslationRepository;
import com.example.demo.repository.ProductTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class StoreService {

    private final LanguageRepository languageRepository;
    private final ProductTranslationRepository productTranslationRepository;
    private final ProductCategoryTranslationRepository productCategoryTranslationRepository;

    @Autowired
    public StoreService(
            LanguageRepository languageRepository,
            ProductTranslationRepository productTranslationRepository,
            ProductCategoryTranslationRepository productCategoryTranslationRepository) {
        this.languageRepository = languageRepository;
        this.productTranslationRepository = productTranslationRepository;
        this.productCategoryTranslationRepository = productCategoryTranslationRepository;
    }

    /**
     * Lấy danh sách tất cả ngôn ngữ
     */
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    /**
     * Lấy danh sách sản phẩm theo ngôn ngữ
     */
    public List<ProductTranslation> getProductsByLanguage(String languageId) {
        return productTranslationRepository.findByLanguageId(languageId);
    }

    /**
     * Lấy danh sách danh mục theo ngôn ngữ
     */
    public List<ProductCategoryTranslation> getCategoriesByLanguage(String languageId) {
        return productCategoryTranslationRepository.findById_LanguageID(languageId);
    }

    /**
     * Nhóm sản phẩm theo danh mục
     */
    public Map<Long, List<ProductTranslation>> groupProductsByCategory(List<ProductTranslation> products) {
        Map<Long, List<ProductTranslation>> productsByCategory = new HashMap<>();
        for (ProductTranslation product : products) {
            Long categoryId = (long) product.getProduct().getProductCategoryID();
            productsByCategory.computeIfAbsent(categoryId, k -> new ArrayList<>()).add(product);
        }
        return productsByCategory;
    }

    /**
     * Lọc sản phẩm theo danh mục
     * Nếu categoryId = null, trả về tất cả sản phẩm
     */
    public List<ProductTranslation> filterProductsByCategory(
            List<ProductTranslation> products,
            Long categoryId) {
        if (categoryId == null) {
            return products; // Mặc định: hiển thị tất cả
        }

        Map<Long, List<ProductTranslation>> productsByCategory = groupProductsByCategory(products);
        return productsByCategory.getOrDefault(categoryId, new ArrayList<>());
    }

    /**
     * Lấy dữ liệu store theo ngôn ngữ và danh mục (tổng hợp)
     */
    public StoreData getStoreData(String languageId, Long categoryId) {
        List<Language> languages = getAllLanguages();
        List<ProductTranslation> allProducts = getProductsByLanguage(languageId);
        List<ProductCategoryTranslation> categories = getCategoriesByLanguage(languageId);
        List<ProductTranslation> filteredProducts = filterProductsByCategory(allProducts, categoryId);

        return new StoreData(languages, categories, filteredProducts, languageId, categoryId);
    }

    /**
     * DTO class để chứa dữ liệu store
     */
    public static class StoreData {
        private final List<Language> languages;
        private final List<ProductCategoryTranslation> categories;
        private final List<ProductTranslation> products;
        private final String selectedLang;
        private final Long selectedCategoryId;

        public StoreData(
                List<Language> languages,
                List<ProductCategoryTranslation> categories,
                List<ProductTranslation> products,
                String selectedLang,
                Long selectedCategoryId) {
            this.languages = languages;
            this.categories = categories;
            this.products = products;
            this.selectedLang = selectedLang;
            this.selectedCategoryId = selectedCategoryId;
        }

        public List<Language> getLanguages() {
            return languages;
        }

        public List<ProductCategoryTranslation> getCategories() {
            return categories;
        }

        public List<ProductTranslation> getProducts() {
            return products;
        }

        public String getSelectedLang() {
            return selectedLang;
        }

        public Long getSelectedCategoryId() {
            return selectedCategoryId;
        }
    }
}

