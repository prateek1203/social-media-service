package com.example.socialmedia.entity;

import java.util.*;

public class User {
    private Long id;
    private String name;
    private String emailId;
    private Set<Long> postIds = new HashSet<>();
    private Set<Long> following = new HashSet<>();

    public User(){}

    public User(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.emailId = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<Long> getPostIds() {
        return postIds;
    }

    public void setPostIds(Set<Long> postIds) {
        this.postIds = postIds;
    }

    public Set<Long> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Long> following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "User(" +
                "id=" + id +
                ", name=" + name +
                ", emailId=" + emailId +
                ", postIds=" + postIds +
                ", following=" + following +
                ')';
    }
}
