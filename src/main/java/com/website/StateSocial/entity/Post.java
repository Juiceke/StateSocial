package com.website.StateSocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

//import javax.persistence.*;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Long postId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    @JsonIgnore
    private State state;

//    @Transient
    @JsonView(View.Public.class)
    private String stateName;

    @JsonView(View.Public.class)
    private String visitorName;

//    @JsonView(View.Internal.class)
//    private Long stateId;

//    @JsonView(View.Internal.class)
//    @Column(insertable=false, updatable=false)
//    private Long visitorId;
    @ManyToOne
//    @JoinColumn(name = "visitorId", referencedColumnName = "visitor_id")
    @JsonIgnoreProperties({"posts", "likedPosts", "password", "email", "visitorId"})
    private User userPosted;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "visitor_id", nullable = false)
    @JsonIgnore
    private String visitor;

    @Column(name = "post_title")
    @JsonView(View.Public.class)
    private String postTitle;

    @Column(name = "post_body")
    @JsonView(View.Public.class)
    private String postBody;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "visitorId", referencedColumnName = "visitor_id")
    @JsonIgnore
    private User userLiked;


    @ManyToMany
    @JoinColumn(name = "visitorId")
    @JsonIgnoreProperties({"posts", "likedPosts", "password", "email", "visitorId"})
    private Set<User> likes;

    private long likeAmnt;

    public Post() {


    }

    public Post(Long postId, String postTitle, String postBody) {
        super();
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }

//    public State getState() {
//        return state;
//    }
//
//    public void setState(State state) {
//        this.state = state;
//    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }


//    public User getVisitor() {
//        return visitor;
//    }
//
//    public void setVisitor(User visitor) {
//        this.visitor = visitor;
//    }


//    public Long getVisitorId() {
//        return visitorId;
//    }
//
//    public void setVisitorId(Long visitorId) {
//        this.visitorId = visitorId;
//    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public long getLikeamnt() {
        return likeAmnt;
    }

    public void setLikeamnt(long likeamnt) {
        this.likeAmnt = likeamnt;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

//    public Set<Like> getLikesTest() {
//        return likesTest;
//    }
//
//    public void setLikesTest(Set<Like> likesTest) {
//        this.likesTest = likesTest;
//    }

    public User getUserPosted() {
        return userPosted;
    }

    public void setUserPosted(User userPosted) {
        this.userPosted = userPosted;
    }

    public User getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(User userLiked) {
        this.userLiked = userLiked;
    }
}

