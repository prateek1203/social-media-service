package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final Set<Post> postSet = new TreeSet<>();

    @Override
    public Set<Post> getAllPosts() {
        return postSet;
    }

    @Override
    public void createOrUpdatePost(Post post) {
        postSet.add(post);
    }

    @Override
    public Optional<Post> getPostById(Long postId) {
        return postSet
                .stream()
                .filter(e -> e.getId().equals(postId))
                .findFirst();
    }
}
