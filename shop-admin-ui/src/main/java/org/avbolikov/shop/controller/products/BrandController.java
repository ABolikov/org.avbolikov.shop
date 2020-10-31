package org.avbolikov.shop.controller.products;

import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.representation.products.BrandRepr;
import org.avbolikov.shop.service.products.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class BrandController {

    @Autowired
    private final BrandServiceImpl brandService;

    @Autowired
    public BrandController(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/admin/brands")
    public String allBrand(Model model) {
        model.addAttribute("activePage", "Brand");
        model.addAttribute("brands", brandService.findAll());
        return "brands";
    }

    @GetMapping("/admin/brands/add")
    public String formAddBrand(Model model) {
        model.addAttribute("activePage", "Brand");
        model.addAttribute("brand", new BrandRepr());
        return "brand";
    }

    @PostMapping("/admin/brand/add")
    public String addProduct(@Valid BrandRepr brandRepr, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "Brand");
            return "brand";
        }
        brandService.save(brandRepr);
        return "redirect:/admin/brands";
    }

    @GetMapping("/admin/brand/{id}/edit")
    public String getBrand(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("activePage", "Brand");
        model.addAttribute("brand", brandService.findById(id).orElseThrow(new NotFoundException("Brand")));
        return "brand";
    }

    @DeleteMapping("/admin/brand/{id}/delete")
    public String deleteBrand(@PathVariable("id") Integer id) {
        brandService.delete(id);
        return "redirect:/admin/brands";
    }
}
