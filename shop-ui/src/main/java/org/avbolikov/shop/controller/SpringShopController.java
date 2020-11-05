package org.avbolikov.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringShopController {

    @GetMapping("/index")
    public String getHomePage() {
        return "index";
    }
}
