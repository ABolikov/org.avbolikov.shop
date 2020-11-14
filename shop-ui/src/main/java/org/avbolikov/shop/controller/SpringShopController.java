package org.avbolikov.shop.controller;

import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.BrandRepository;
import org.avbolikov.shop.repositories.CategoryRepository;
import org.avbolikov.shop.representation.LineItem;
import org.avbolikov.shop.service.CartServiceImpl;
import org.avbolikov.shop.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpringShopController {

    private final ProductServiceImpl productService;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final CartServiceImpl cartService;

    @Autowired
    public SpringShopController(ProductServiceImpl productService,
                                CategoryRepository categoryRepository,
                                BrandRepository brandRepository,
                                CartServiceImpl cartService) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String redirectHomePage() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String getHomePage(Model model) {
        model.addAttribute("activePage", "Home");
        model.addAttribute("brands", brandRepository.findAll());
        return "index";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "products";
    }

    @GetMapping("/product/{id}")
    public String getBrand(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow(new NotFoundException("Product")));
        return "product";
    }

    @PostMapping("/product")
    public String addProductCart(LineItem lineItem) {
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.updateCart(lineItem);
        return "redirect:/products";
    }
}
