package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class StoreController {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ProductTranslationRepository productTranslationRepository;

    @Autowired
    private ProductCategoryTranslationRepository productCategoryTranslationRepository;

    @GetMapping("/")
    public String index(
            @RequestParam(name = "lang", defaultValue = "vi") String lang,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            Model model) {

        List<Language> languages = languageRepository.findAll();
        List<ProductTranslation> products = productTranslationRepository.findByLanguageId(lang);
        List<ProductCategoryTranslation> categories = productCategoryTranslationRepository.findById_LanguageID(lang);

        // Tạo map nhóm sản phẩm theo danh mục
        Map<Long, List<ProductTranslation>> productsByCategory = new HashMap<>();
        for (ProductTranslation product : products) {
            Long catId = (long) product.getProduct().getProductCategoryID();
            productsByCategory.computeIfAbsent(catId, k -> new ArrayList<>()).add(product);
        }

        // Nếu chọn category → chỉ lấy sản phẩm thuộc category đó
        List<ProductTranslation> filteredProducts;
        if (categoryId != null) {
            filteredProducts = productsByCategory.getOrDefault(categoryId, new ArrayList<>());
        } else {
            filteredProducts = products; // mặc định: hiển thị tất cả
        }

        model.addAttribute("languages", languages);
        model.addAttribute("categories", categories);
        model.addAttribute("products", filteredProducts);
        model.addAttribute("selectedLang", lang);
        model.addAttribute("selectedCategoryId", categoryId);

        return "index";
    }
}
