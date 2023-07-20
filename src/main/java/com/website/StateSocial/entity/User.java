package com.website.StateSocial.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
//import javax.persistence.*;
//import javax.transaction.Transactional;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "visitor")

//@JsonFilter("UserFilter")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_id")
    private Long visitorId;

    private Long postId;

    @Column(name = "username", nullable = false, unique = true, length = 32)
    private String userName;

    @Column(name = "password", nullable = false, unique = true)
    private char[] password;

    @Column(name = "email", nullable = false, unique = true, length = 249)
    private char[] email;

    @ManyToMany(mappedBy = "likes", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"userPosted", "likes"})
    private Set<Post> likedPosts;

    @Transient
    boolean loggedIn;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userPosted")
    @Column(name = "user_post")
//    @JsonIgnoreProperties()
    private List<Post> posts;

    public User() {

    }

    public User(Long visitorId, String userName, char[] password, char[] email) {
        super();
        this.visitorId = visitorId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public char[] getEmail() {
        return email;
    }

    public void setEmail(char[] email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return loggedIn == user.loggedIn && Objects.equals(visitorId, user.visitorId) && Objects.equals(userName, user.userName) && Arrays.equals(password, user.password) && Arrays.equals(email, user.email) && Objects.equals(posts, user.posts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(visitorId, userName, loggedIn, posts);
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + Arrays.hashCode(email);
        return result;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User)) return false;
//        User user = (User) o;
//        return loggedIn == user.loggedIn && Objects.equals(visitorId, user.visitorId) && Objects.equals(postId, user.postId) && Objects.equals(userName, user.userName) && Arrays.equals(password, user.password) && Arrays.equals(email, user.email) && Objects.equals(likedPosts, user.likedPosts) && Objects.equals(posts, user.posts);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(visitorId, postId, userName, likedPosts, loggedIn, posts);
//        result = 31 * result + Arrays.hashCode(password);
//        result = 31 * result + Arrays.hashCode(email);
//        return result;
//    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "visitorId=" + visitorId +
//                ", userName='" + userName + '\'' +
//                ", password=" + Arrays.toString(password) +
//                ", email=" + Arrays.toString(email) +
//                ", loggedIn=" + loggedIn +
//                ", posts=" + posts +
//                '}';
//    }

    @Override
    public String toString() {
        return "User{" +
                "visitorId=" + visitorId +
                ", userName='" + userName + '\'' +
                ", password=" + Arrays.toString(password) +
                ", email=" + Arrays.toString(email) +
                ", loggedIn=" + loggedIn +
                ", posts=" + posts +
                '}';
    }
}
