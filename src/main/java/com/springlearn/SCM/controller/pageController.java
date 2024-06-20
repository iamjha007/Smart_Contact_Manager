package com.springlearn.SCM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageController {


    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/hometest")
    public String home(Model model) {
        model.addAttribute("name", "SCM");
        model.addAttribute("githubRepo", "https://github.com/iamjha007/Smart_Contact_Manager");
        model.addAttribute("instagramLink","https://www.instagram.com/iamjha007/");
        return "hometest";
    }

    @RequestMapping("/abouttest")
    public String aboutTest() {
        return "abouttest";
    }

}
