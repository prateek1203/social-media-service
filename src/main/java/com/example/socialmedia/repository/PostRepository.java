package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository {

    Set<Post> getAllPosts();

    void createOrUpdatePost(Post post);

    Optional<Post> getPostById(Long postId) throws Exception;
}
