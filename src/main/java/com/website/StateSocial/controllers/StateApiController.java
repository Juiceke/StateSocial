package com.website.StateSocial.controllers;

//import com.website.StateSocial.entity.Like;
import com.website.StateSocial.entity.Post;
import com.website.StateSocial.entity.State;
import com.website.StateSocial.entity.User;

import com.website.StateSocial.repository.UserRepository;
import com.website.StateSocial.service.StateService;
//import jakarta.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;



@RestController
public class StateApiController {

    @Autowired
    UserRepository userRepository;

    private StateService stateService;

    public StateApiController(StateService stateService) {
        super();
        this.stateService = stateService;
    }

    @GetMapping("/states/api")
    public List<State> getStates() {
        return stateService.getAllStates();
    }

    @GetMapping("/states/{stateId}/api")
    public State getStates(@PathVariable Long stateId) {
        return stateService.getStateById(stateId);
    }

    @GetMapping("/session")
    public User getSessionUser(Model model, HttpServletRequest request) {
        User sessionUser = new User();
        sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
       return stateService.getUserById(sessionUser.getVisitorId());
    }

//    @PostMapping(value = "/posts/like", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, headers = {})
//    public @ResponseBody void likePost(@RequestParam Long postId, @RequestParam Long visitorId, @ModelAttribute State state,
//                                       HttpServletRequest request){
//        User sessionUser = new User();
//        sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
////        check to make sure html wasn't tampered with or just is funky
//        if(sessionUser.getVisitorId().equals(visitorId)) {
//            System.out.println(postId);
//            System.out.println(visitorId);
//            Post post = stateService.getPostById(postId);
//            User user = stateService.getUserById(visitorId);
//
//            String addedLikedName = user.getUserName();
//            Long addLikedId = user.getVisitorId();
//
//            post.getLikes().add(user);
//            post.setLikeamnt(post.getLikes().size());
//
//            stateService.saveUser(user);
//            stateService.savePost(post);
//        }
//    }

////    very strange, but works!
//    @PostMapping("/posts/api")
//    public String createPost(@ModelAttribute Post postRequest, @ModelAttribute User userRequest,
//                             @ModelAttribute State stateRequest, HttpServletRequest request) {
//
//        User sessionUser = new User();
//        sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
//
//        if(sessionUser.getVisitorId() != userRequest.getVisitorId()) {
//            return "create_post";
//        }
//
//        State state = stateService.getStateById(stateRequest.getStateId());
//        User user = stateService.getUserById(userRequest.getVisitorId());
//
//        postRequest.setStateName(state.getStateName());
//        postRequest.setState(state);
//        postRequest.setVisitorName(user.getUserName());
//        postRequest.setUserPosted(user);
////        postRequest.setVisitorId(user.getVisitorId());
////        postRequest.setLikes(0);
////        postRequest.setVisitor(user);
//
//        stateService.savePost(postRequest);
//        return "redirect:/";
//    }
//
//    @PostMapping("/posts/{visitorId}/{postId}/like/api")
//    public List<Post> likePost(@PathVariable(value = "postId") Long postId, @PathVariable(value = "visitorId")
//            Long visitorId){
//        Post post = stateService.getPostById(postId);
//        User user = stateService.getUserById(visitorId);
//
//        String addedLikedName = user.getUserName();
//        Long addLikedId = user.getVisitorId();
//
//        post.getLikes().add(user);
//        post.setLikeamnt(post.getLikes().size());
//
//        stateService.saveUser(user);
//        stateService.savePost(post);
//        return stateService.getAllPosts();
//    }

}
