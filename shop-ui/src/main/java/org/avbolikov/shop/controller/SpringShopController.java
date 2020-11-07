package org.avbolikov.shop.controller;

import org.avbolikov.shop.repositories.BrandRepository;
import org.avbolikov.shop.repositories.CategoryRepository;
import org.avbolikov.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringShopController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public SpringShopController(BrandRepository brandRepository,
                                ProductRepository productRepository,
                                CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String redirectHomePage() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String getHomePage(Model model) {
        model.addAttribute("activePage", "Home");
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "index";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "products";
    }
}
