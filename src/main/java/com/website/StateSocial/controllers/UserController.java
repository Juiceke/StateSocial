package com.website.StateSocial.controllers;

import com.website.StateSocial.repository.UserRepository;
import com.website.StateSocial.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    private StateService stateService;

    public UserController(StateService stateService) {
        super();
        this.stateService = stateService;
    }

//    @GetMapping("/user")
//    public String UserPage(Model model) {
////        System.out.println(visitorId);
////        int five = 2;
////        Long Longfive = 1L;
////        userRepository.findById(Longfive);
//        return "user_page";
//    }
}
