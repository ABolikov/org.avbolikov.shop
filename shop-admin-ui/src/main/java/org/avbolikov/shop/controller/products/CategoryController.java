package org.avbolikov.shop.controller.products;

import org.avbolikov.shop.entity.products.Category;
import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/admin/categories")
    public String allCategories(Model model) {
        model.addAttribute("activePage", "Category");
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String formAddCategory(Model model) {
        model.addAttribute("activePage", "Category");
        model.addAttribute("category", new Category());
        return "category";
    }

    @PostMapping("/admin/category/add")
    public String addCategory(@Valid Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "Category");
            return "category";
        }
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/category/{id}/edit")
    public String getCategory(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("activePage", "Category");
        model.addAttribute("category",
                categoryRepository.findById(id).orElseThrow(new NotFoundException("Category")));
        return "category";
    }

    @DeleteMapping("/admin/category/{id}/delete")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/categories";
    }
}
