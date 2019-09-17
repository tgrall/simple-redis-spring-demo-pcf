package com.redislabs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Le Monde") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}

