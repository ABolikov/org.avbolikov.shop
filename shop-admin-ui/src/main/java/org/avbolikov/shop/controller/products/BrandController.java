package org.avbolikov.shop.controller.products;

import org.avbolikov.shop.entity.products.Brand;
import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.BrandRepository;
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
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/admin/brands")
    public String allBrand(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        return "brands";
    }

    @GetMapping("/admin/brands/add")
    public String formAddBrand(Model model) {
        model.addAttribute("brand", new Brand());
        return "brand";
    }

    @PostMapping("/admin/brand/add")
    public String addProduct(@Valid Brand brand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "brand";
        }
        brandRepository.save(brand);
        return "redirect:/admin/brands";
    }

    @GetMapping("/admin/brand/{id}/edit")
    public String getBrand(@PathVariable("id") Integer id, Model model) {
        Brand brand = brandRepository.findById(id).orElseThrow(new NotFoundException(null, "Brand"));
        model.addAttribute("brand", brand);
        return "brand";
    }

    @DeleteMapping("/admin/brand/{id}/delete")
    public String deleteBrand(@PathVariable("id") Integer id) {
        brandRepository.deleteById(id);
        return "redirect:/admin/brands";
    }
}
