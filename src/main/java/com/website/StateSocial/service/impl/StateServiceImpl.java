package com.website.StateSocial.service.impl;

import com.website.StateSocial.entity.Post;
import com.website.StateSocial.entity.State;
import com.website.StateSocial.entity.User;
import com.website.StateSocial.repository.PostRepository;
import com.website.StateSocial.repository.StateRepository;
import com.website.StateSocial.repository.UserRepository;
import com.website.StateSocial.service.StateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringTokenizer;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public StateServiceImpl(StateRepository stateRepository, PostRepository postRepository, UserRepository userRepository) {
        super();
        this.stateRepository = stateRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<State> getAllStates() { return stateRepository.findAll(); }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllPostsByState(String state_id) {
        return null;
    }

    @Override
    public Post getPostById(Long post_id) { return postRepository.findById(post_id).get(); }
    @Override
    public State getStateById(Long state_id) {
        return stateRepository.findById(state_id).get();
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long visitor_id) {
        return userRepository.findById(visitor_id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



//    @Override
//    public StringTokenizer findUserByEmail(String email) {
//        StringTokenizer st = new StringTokenizer(email, "@");
//
//        return st;
//    }

//    @Override
//    public List<Post> getAllPostsById() { return stateRepository.findById(state_id).get();}
}