package com.website.StateSocial.service;

import com.website.StateSocial.entity.Post;
import com.website.StateSocial.entity.State;
import com.website.StateSocial.entity.User;

import java.util.List;

public interface StateService {
    List<State> getAllStates();

    List<Post> getAllPosts();

    Post getPostById(Long post_id);

    State getStateById(Long state_id);

    Post savePost(Post post);

    User saveUser(User user);

    User getUserById(Long visitor_id);

    List<User> getAllUsers();

    List<Post> findBystateName(String state, int amount);

//    List<Post> getAllPostsByState(Long state_id);

//    StringTokenizer findUserByEmail(String email);
}
