package com.website.StateSocial.repository;

import com.website.StateSocial.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findBystateName(String stateName);
}
