package org.avbolikov.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @GetMapping("/")
    public String redirectAdminPage() {
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }
}
