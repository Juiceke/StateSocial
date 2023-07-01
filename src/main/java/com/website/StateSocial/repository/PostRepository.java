package com.website.StateSocial.repository;

import com.website.StateSocial.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
