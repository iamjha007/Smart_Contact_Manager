package com.springlearn.SCM.controller;

import com.springlearn.SCM.entity.User;
import com.springlearn.SCM.misc.LoggedInUserEmail;
import com.springlearn.SCM.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile(Model model,Authentication authentication) {

        return "user/profile";
    }

    @GetMapping("/contact")
    public String Contact() {
        return "user/contact";
    }
}
