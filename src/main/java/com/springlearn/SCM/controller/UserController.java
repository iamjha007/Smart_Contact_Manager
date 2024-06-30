package com.springlearn.SCM.controller;

import com.springlearn.SCM.misc.LoggedInUserEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile(Authentication authentication) {

        LoggedInUserEmail.getLoggedInUserEmail(authentication);
        return "user/profile";
    }

    @GetMapping("/contact")
    public String Contact() {
        return "user/contact";
    }
}
