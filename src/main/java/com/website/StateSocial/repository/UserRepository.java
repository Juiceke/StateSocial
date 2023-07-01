package com.website.StateSocial.repository;

import com.website.StateSocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select distinct u from visitor u left join fetch u.post")
//    List<User> findAllUsers();

    User findUserByEmail(char[] email) throws Exception;
}
