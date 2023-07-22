package com.retail.kynaara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/redirect")
public class RedirectController {

    @GetMapping("")
    public String redirect(ModelMap modelMap, @RequestParam String q){
        modelMap.addAttribute("product_url", q);
        return "redirect";
    }

}
