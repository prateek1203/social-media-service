package com.example.socialmedia.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post implements Comparable<Post> {

    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Post(Long id, Long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    @Override
    public int compareTo(Post post) {
        return this.getCreatedAt().compareTo(post.getCreatedAt()) * -1; // for descending order
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return content.equals(post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
