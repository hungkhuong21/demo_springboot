package com.example.demo.controller;

import com.example.demo.entity.Language;
import com.example.demo.entity.ProductTranslation;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.repository.ProductTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ProductTranslationRepository productTranslationRepository;

    @GetMapping("/")
    public String index(@RequestParam(name = "lang", defaultValue = "vi") String lang, Model model) {

        List<Language> languages = languageRepository.findAll();
        List<ProductTranslation> products = productTranslationRepository.findByIdLanguageId(lang);

        model.addAttribute("languages", languages);
        model.addAttribute("products", products);
        model.addAttribute("selectedLang", lang);

        return "index";
    }
}
