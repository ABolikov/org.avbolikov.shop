package org.avbolikov.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }
}
