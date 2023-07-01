package com.website.StateSocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

//import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "state")
public class State implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    @JsonView(View.Public.class)
    private Long stateId;

    @Column(name = "state_name", nullable = false, unique = true, length = 32)
    @JsonView(View.Public.class)
    private String stateName;

    @Column(name = "state_abbr", unique = true)
    @JsonView(View.Public.class)
    private String stateAbbr;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "state", cascade = CascadeType.ALL)
    @Column(name = "state_posts")
    @JsonView(View.Public.class)
    private List<Post> posts;

    public State() {


    }

    public State(String stateName, String stateAbbr) {
        super();
        this.stateName = stateName;
        this.stateAbbr = stateAbbr;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
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
        if (!(o instanceof State state)) return false;
        return Objects.equals(stateId, state.stateId) && Objects.equals(stateName, state.stateName) && Objects.equals(stateAbbr, state.stateAbbr) && Objects.equals(posts, state.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateId, stateName, stateAbbr, posts);
    }

    @Override
    public String toString() {
        return "State{" +
                "stateId=" + stateId +
                ", stateName='" + stateName + '\'' +
                ", stateAbbr='" + stateAbbr + '\'' +
                ", posts=" + posts +
                '}';
    }
}
