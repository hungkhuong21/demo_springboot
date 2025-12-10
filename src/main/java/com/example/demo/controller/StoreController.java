package com.example.demo.controller;

import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(name = "lang", defaultValue = "vi") String lang,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            Model model) {

        // Lấy dữ liệu từ Service layer (business logic đã được xử lý trong Service)
        StoreService.StoreData storeData = storeService.getStoreData(lang, categoryId);

        // Thêm dữ liệu vào Model để truyền sang View
        model.addAttribute("languages", storeData.getLanguages());
        model.addAttribute("categories", storeData.getCategories());
        model.addAttribute("products", storeData.getProducts());
        model.addAttribute("selectedLang", storeData.getSelectedLang());
        model.addAttribute("selectedCategoryId", storeData.getSelectedCategoryId());

        return "index";
    }
}
