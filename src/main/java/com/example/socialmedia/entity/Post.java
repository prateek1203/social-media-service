package com.example.socialmedia.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post implements Comparable<Post> {

    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Post() {
    }

    public Post(Long id, Long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "Post(" +
                "id=" + id +
                ", userId=" + userId +
                ", content=" + content +
                ", createdAt=" + createdAt +
                ')';
    }
}
