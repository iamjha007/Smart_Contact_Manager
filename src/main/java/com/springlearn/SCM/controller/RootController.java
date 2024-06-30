package com.springlearn.SCM.controller;

import com.springlearn.SCM.entity.User;
import com.springlearn.SCM.misc.LoggedInUserEmail;
import com.springlearn.SCM.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@Slf4j
public class RootController {

    // The data returned or modified by this method will be added to the model automatically.
    // @ModelAttribute annotation is used to add data to the model
    //key for user ao that it can be used in the template wi;; be "loggedInUser"
    @Autowired
    private UserService userService;
    @ModelAttribute("loggedInUser")
    public User addLoginDataToModel(Model model, Authentication authentication){

        if (authentication == null) {
            log.info("authentication is null");
            return null;
        }
        String userName= LoggedInUserEmail.getLoggedInUserEmail(authentication);
        User user=userService.getUserByEmail(userName);

        return user;
//        model.addAttribute("loggedInUser",user);
    }
}
