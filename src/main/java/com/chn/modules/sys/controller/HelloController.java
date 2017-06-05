package com.chn.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "World");
        model.addAttribute("title", 1);
        return "home";
    }
    
}
