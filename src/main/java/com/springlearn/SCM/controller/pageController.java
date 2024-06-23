package com.springlearn.SCM.controller;

import com.springlearn.SCM.entity.User;
import com.springlearn.SCM.forms.UserForm;
import com.springlearn.SCM.misc.Message;
import com.springlearn.SCM.misc.MessageType;
import com.springlearn.SCM.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class pageController {


    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
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

    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm= new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping(value = "/do-register")
    public String doRegister(@Valid @ModelAttribute UserForm userForm, BindingResult result, HttpSession session) {
        System.out.println("do register");
        System.out.println(userForm);


        if(result.hasErrors()){
            return "register";
        }
        //create user to save in the database

        User user = User.builder()
                .name(userForm.getName())
                .phone(userForm.getPhone())
                .password(userForm.getPassword())
                .email(userForm.getEmail())
                .about(userForm.getAbout())
                .profilePic("/resources/static/images/profile.png")
                .build();
        User savedUser = userService.saveUser(user);

        Message message = Message.builder()
                .message("User Registered Successfully!")
                .type(MessageType.green)
                .build();

        session.setAttribute("message", message);
        log.info("user registered successfully"+savedUser);
        return "redirect:/register";
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
