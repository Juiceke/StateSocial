package com.website.StateSocial.controllers;

//import com.website.StateSocial.entity.Like;
import com.website.StateSocial.entity.Post;
import com.website.StateSocial.entity.State;
import com.website.StateSocial.entity.User;

import com.website.StateSocial.repository.UserRepository;
import com.website.StateSocial.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping("/posts/{stateId}/{visitorId}/api")
    public State createPost(@PathVariable(value = "stateId") Long stateId, @PathVariable(value = "visitorId")
    Long visitorId,@RequestBody Post postRequest) {
        State state = stateService.getStateById(stateId);
        User user = stateService.getUserById(visitorId);
        postRequest.setStateName(state.getStateName());
        postRequest.setState(state);
        postRequest.setVisitorName(user.getUserName());
        postRequest.setUserPosted(user);
//        postRequest.setVisitorId(user.getVisitorId());
//        postRequest.setLikes(0);
//        postRequest.setVisitor(user);

        stateService.savePost(postRequest);
        return stateService.getStateById(stateId);
    }

    @PostMapping("/posts/{visitorId}/{postId}/like/api")
    public List<Post> likePost(@PathVariable(value = "postId") Long postId, @PathVariable(value = "visitorId")
            Long visitorId){
        Post post = stateService.getPostById(postId);
        User user = stateService.getUserById(visitorId);

        String addedLikedName = user.getUserName();
        Long addLikedId = user.getVisitorId();

        post.getLikes().add(user);
        post.setLikeamnt(post.getLikes().size());

        stateService.saveUser(user);
        stateService.savePost(post);
        return stateService.getAllPosts();
    }

}
