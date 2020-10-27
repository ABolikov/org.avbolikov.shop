package org.avbolikov.shop.controller.products;

import org.avbolikov.shop.entity.products.Product;
import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.BrandRepository;
import org.avbolikov.shop.repositories.CategoryRepository;
import org.avbolikov.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/admin/products")
    public String allProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String formAddProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("allCategories",  categoryRepository.findAll());
        model.addAttribute("brands",  brandRepository.findAll());
        return "product";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allCategories",  categoryRepository.findAll());
            model.addAttribute("brands",  brandRepository.findAll());
            return "product";
        }
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/{id}/edit")
    public String getProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(new NotFoundException(null, "Product"));
        model.addAttribute("product", product);
        model.addAttribute("allCategories",  categoryRepository.findAll());
        model.addAttribute("brands",  brandRepository.findAll());
        return "product";
    }

    @DeleteMapping("/admin/product/{id}/delete")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }
}
