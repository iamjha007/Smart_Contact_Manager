package com.springlearn.SCM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "SCM");
        model.addAttribute("githubRepo", "https://github.com/iamjha007/Smart_Contact_Manager");
        model.addAttribute("instagramLink","https://www.instagram.com/iamjha007/");
        return "home";
    }
}
