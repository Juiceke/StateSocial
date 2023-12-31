package com.website.StateSocial.controllers;

import com.fasterxml.jackson.core.JsonToken;
import com.website.StateSocial.entity.Post;
import com.website.StateSocial.entity.State;
import com.website.StateSocial.entity.User;
import com.website.StateSocial.repository.UserRepository;
import com.website.StateSocial.service.StateService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import javax.servlet.http.HttpServletRequest;
//import java.sql.SQLIntegrityConstraintViolationException;
//import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StateController {

    private StateService stateService;

    @Autowired
    private UserRepository userRepository;

    public StateController(StateService stateService) {
        super();
        this.stateService = stateService;
    }

//    public void getSessionUser(Model model, HttpServletRequest request) {
//        User sessionUser = new User();
//
//        if (request.getSession(false) != null) {
//            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//            model.addAttribute("user", sessionUser);
////            System.out.println(request.getSession().getAttribute("SESSION_USER"));
//            model.addAttribute("loggedIn", sessionUser.isLoggedIn());
//        } else {
//            model.addAttribute("loggedIn", false);
//        }
//
//        model.addAttribute("loggedIn", sessionUser.isLoggedIn());
//    }

    @GetMapping("/specific")
    public String beSpecific(Model model, HttpServletRequest request, @ModelAttribute State state) {
        model.addAttribute("states", stateService.getAllStates());
        User sessionUser = new User();

        if (request.getSession(false).getAttribute("SESSION_USER") != null) {
            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//            System.out.println(request.getSession().getAttribute("SESSION_USER"));
            model.addAttribute("loggedIn", sessionUser.isLoggedIn());
            model.addAttribute("user", sessionUser);
        } else {
            model.addAttribute("loggedIn", false);
            return "redirect:/login";
        }


        return "Specific";
    }

    @GetMapping("/create")
    public String no() {
        return "redirect:/";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, Model model, @ModelAttribute State state,
                             HttpServletRequest request) {
        model.addAttribute("states", stateService.getAllStates());
        System.out.println(state);
        User sessionUser = new User();

        if (request.getSession(false) != null) {
            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//            System.out.println(request.getSession().getAttribute("SESSION_USER"));
            model.addAttribute("loggedIn", sessionUser.isLoggedIn());
            model.addAttribute("user", sessionUser);
        } else {
            model.addAttribute("loggedIn", false);
            return "redirect:/login";
        }

        return "create_post";
    }

    //    very strange, but works!
    @PostMapping("/posts/api")
    public String createPost(@ModelAttribute Post postRequest, @ModelAttribute User userRequest,
                             @ModelAttribute State stateRequest, HttpServletRequest request,
                             @RequestParam Long stateId, Model model) {

//        request.getSession().setAttribute("SESSION_STATE", stateId);
//        Long sessionState = (Long) request.getSession().getAttribute("SESSION_STATE");
//        System.out.println(sessionState);

//        get user session
        User sessionUser = new User();
        sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//        check to make sure html wasn't tampered with or just is funky
        if(sessionUser.getVisitorId().equals(userRequest.getVisitorId())) {
//            create the post
            State state = stateService.getStateById(stateRequest.getStateId());
            User user = stateService.getUserById(userRequest.getVisitorId());
            System.out.println("og = " + postRequest.getPostTitle());
            System.out.println("escaped = " + postRequest.getPostTitle().replaceAll("\\r", ""));

            postRequest.setPostTitle(postRequest.getPostTitle().replaceAll("\\r\\n", "\n"));

            postRequest.setPostBody(postRequest.getPostBody().replaceAll("\\r\\n", "\n"));

            postRequest.setStateName(state.getStateName());
            postRequest.setStatesId(state.getStateId());
            postRequest.setState(state);
            postRequest.setVisitorName(user.getUserName());
            postRequest.setUserPosted(user);

            if(postRequest.getPostTitle().replaceAll("\\r", "").length() > 255
               || postRequest.getPostBody().length() > 4000) {
                System.out.println("important " + postRequest.getPostTitle().length());
                model.addAttribute("notice",
                        "Content longer than it should be!");
                return "create_post";
            }

            stateService.savePost(postRequest);
            return "redirect:/";
        } else {
           model.addAttribute("notice", "user making post is not the same as user in database. try returning to homepage");
            return "create_post";
        }
    }

//    @PostMapping("/posts")
//    public void sessionState(Model model, @ModelAttribute State state, HttpServletRequest request) {
////        model.addAttribute("states", stateService.getAllStates());
//        State sessionState = new State();
//        sessionState = (State) request.getSession().getAttribute("SESSION_STATE");
//        request.getSession().setAttribute("SESSION_STATE", state);
//        System.out.println(sessionState);
//    }

    @GetMapping("/posts")
    public String posts(Model model, @ModelAttribute State state, @ModelAttribute Post post, HttpServletRequest request,
                        @RequestParam Long stateId) {
        model.addAttribute("states", stateService.getAllStates());
        request.getSession().setAttribute("SESSION_STATE", stateId);
        System.out.println(stateId);
//        State sessionState = new State();
        Long sessionState = (Long) request.getSession().getAttribute("SESSION_STATE");
        System.out.println(sessionState);
        User sessionUser = new User();

        if (request.getSession().getAttribute("SESSION_USER") != null) {
            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//            System.out.println(request.getSession().getAttribute("SESSION_USER"));
            model.addAttribute("user", sessionUser);
            model.addAttribute("loggedIn", sessionUser.isLoggedIn());
        } else {
            model.addAttribute("loggedIn", false);
        }

        model.addAttribute("loggedIn", sessionUser.isLoggedIn());
        if(sessionState > 50 || sessionState < 0) {
            model.addAttribute("notice", "state selected outside of what was expected. please try again.");
        }
        else {
            model.addAttribute("posts", stateService.findBystateName(stateService.getStateById(sessionState).getStateName(), 0));
        }
        return "landing_page";
    }

//    private Long getPosts(Long state_id) {
//        System.out.println();
//        return stateService.getStateById(state_id).getStateId();
//    }

    @PostMapping(value = "/posts/like", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody Post likePost(@RequestParam Long postId, @RequestParam Long visitorId,
                                       @ModelAttribute State state, HttpServletRequest request){

        User sessionUser = new User();
        sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//        check to make sure html wasn't tampered with or just is funky
        if(sessionUser.getVisitorId().equals(visitorId)) {


            Post post = stateService.getPostById(postId);
            User user = stateService.getUserById(visitorId);

            System.out.println("HEEEEYEYYYYY " + visitorId);
            System.out.println(post.getLikes().contains(user));

            String addedLikedName = user.getUserName();
            Long addLikedId = user.getVisitorId();

            if(post.getLikes().contains(user) && user.getLikedPosts().contains(post)) {

                    System.out.println("ayyy");
                    post.getLikes().remove(user);
                    post.setLikeamnt(post.getLikes().size());
                    return stateService.savePost(post);
                } else if(!post.getLikes().contains(user) && !user.getLikedPosts().contains(post)){
                System.out.println("whyyyy");
                post.getLikes().add(user);
                post.setLikeamnt(post.getLikes().size());

                return stateService.savePost(post);
            }
        }
        return new Post();
    }

    @GetMapping("/")
    public String landingPage(Model model, HttpServletRequest request, @ModelAttribute State state) {
        Long baseNumber = 1L;

        System.out.println(baseNumber);

        request.getSession().setAttribute("SESSION_STATE", 1L);

//        request.getSession(false).setAttribute("states", stateService.getAllStates());
        System.out.println(request.getSession().getAttribute("SESSION_STATE"));

        model.addAttribute("states", stateService.getAllStates());
        model.addAttribute("posts", stateService.getAllPosts());

        User sessionUser = new User();

        if (request.getSession().getAttribute("SESSION_USER") != null) {
            System.out.println("what");
//            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
            System.out.println(request.getSession().getAttribute("SESSION_USER"));
            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
            model.addAttribute("user", sessionUser);
            model.addAttribute("loggedIn", sessionUser.isLoggedIn());
        } else {
            model.addAttribute("loggedIn", false);
        }

//        model.addAttribute("loggedIn", sessionUser.isLoggedIn());
        return "landing_page";
    }

    @GetMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("SESSION_USER") == null) {
            // obtain cookie now
            request.getSession();
            model.addAttribute("user", new User());
            return "login_page";
        }

        return "redirect:/";
    }

    @GetMapping("/users/logout")
    public String logout(HttpServletRequest request) {
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
            request.removeAttribute("SESSION_USER");
        }
        return "redirect:/login";
    }

    @GetMapping("/{visitorId}/user")
    public String userPage(Model model, @PathVariable Long visitorId, HttpServletRequest request) {
        User sessionUser = new User();

        if (request.getSession(false) != null) {
            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//            System.out.println(request.getSession().getAttribute("SESSION_USER"));
            model.addAttribute("loggedIn", sessionUser.isLoggedIn());
            try {
                model.addAttribute("user", stateService.getUserById(visitorId));
                return "user_page";
            } catch (Exception exception) {
                model.addAttribute("userdont", "user does not exist");
                return "user_page";
            }
        } else {
            model.addAttribute("loggedIn", false);
            return "redirect:/login";
        }

    }

//    @GetMapping("/{visitorId}/user")
//    public ModelAndView user(@PathVariable Long visitorId) {
//        ModelAndView mav = new ModelAndView("user/list");
//        mav.addObject("user", stateService.getUserById(visitorId));
//        return mav;
//    }
}
