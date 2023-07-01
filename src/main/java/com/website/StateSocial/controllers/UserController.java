package com.website.StateSocial.controllers;

import com.website.StateSocial.service.StateService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {
    private StateService stateService;

    public UserController(StateService stateService) {
        super();
        this.stateService = stateService;
    }

//    @GetMapping("/login")
//        public String loginPage(Model model) {
//        return "user_page";
//        }
}
