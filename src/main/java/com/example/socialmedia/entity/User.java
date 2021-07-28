package com.example.socialmedia.entity;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    private String name;
    private String emailId;
    private List<Long> postIds = new ArrayList<>();
    private Set<Long> following = new HashSet<>();

    public User(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.emailId = email;
    }
}
