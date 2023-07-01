package com.website.StateSocial.controllers;

import com.website.StateSocial.entity.User;
import com.website.StateSocial.repository.UserRepository;
import com.website.StateSocial.service.StateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StateController {

    private StateService stateService;

    @Autowired
    private UserRepository userRepository;

    public StateController(StateService stateService) {
        super();
        this.stateService = stateService;
    }

    @GetMapping("/")
    public String landingPage(Model model, HttpServletRequest request) {
        model.addAttribute("states", stateService.getAllStates());

        User sessionUser = new User();

        if (request.getSession(false) != null) {
            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//            System.out.println(request.getSession().getAttribute("SESSION_USER"));
            model.addAttribute("loggedIn", sessionUser.isLoggedIn());
        } else {
            model.addAttribute("loggedIn", false);
        }

        model.addAttribute("loggedIn", sessionUser.isLoggedIn());
        return "landing_page";
    }

    @GetMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
        if (request.getSession(false) != null) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "login_page";
    }

    @GetMapping("/users/logout")
    public String logout(HttpServletRequest request) {
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }

    @GetMapping("/{visitorId}/user")
    public String userPage(Model model, @PathVariable Long visitorId) {
        model.addAttribute("user", stateService.getUserById(visitorId));
        System.out.println(stateService.getUserById(visitorId));
        return "user_page";
    }

//    @GetMapping("/{visitorId}/user")
//    public ModelAndView user(@PathVariable Long visitorId) {
//        ModelAndView mav = new ModelAndView("user/list");
//        mav.addObject("user", stateService.getUserById(visitorId));
//        return mav;
//    }
}
