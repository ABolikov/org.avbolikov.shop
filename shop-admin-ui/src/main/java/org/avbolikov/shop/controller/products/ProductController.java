package org.avbolikov.shop.controller.products;

import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.BrandRepository;
import org.avbolikov.shop.repositories.CategoryRepository;
import org.avbolikov.shop.representation.products.ProductRepr;
import org.avbolikov.shop.service.products.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProductController {

    private final ProductServiceImpl productService;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    @Autowired
    public ProductController(ProductServiceImpl productService, CategoryRepository categoryRepository,
                             BrandRepository brandRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping("/admin/products")
    public String allProducts(Model model) {
        model.addAttribute("activePage", "Product");
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String formAddProduct(Model model) {
        model.addAttribute("activePage", "Product");
        model.addAttribute("productRepr", new ProductRepr());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "product";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@Valid ProductRepr productRepr, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "Product");
            model.addAttribute("categories",  categoryRepository.findAll());
            model.addAttribute("brands",  brandRepository.findAll());
            return "product";
        }
        productService.save(productRepr);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/{id}/edit")
    public String getProduct(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("activePage", "Product");
        model.addAttribute("productRepr",
                productService.findById(id).orElseThrow(new NotFoundException("Product")));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "product";
    }

    @DeleteMapping("/admin/product/{id}/delete")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}
