package com.example.socialmedia.service;

import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.RequiredParameterMissingException;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    private static final int NEWS_FEED_LIMIT = 20;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Post createPost(Long userId, String content) {
        if (Strings.isBlank(content)){
            throw new RequiredParameterMissingException("Blank Post can not be created");
        }
        Set<Post> postSet = postRepository.getAllPosts();
        Long postId = postSet.isEmpty() ? 1L : postSet.size() + 1;
        this.createPostForUser(userId, postId);
        Post newPost = new Post(postId, userId, content);
        postRepository.createOrUpdatePost(newPost);
        return newPost;
    }

    public void createPostForUser(Long userId, Long postId) {
        User user = userRepository.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user found"));
        user.getPostIds().add(postId);
        userRepository.createOrUpdateUser(user);
    }

    public List<String> getNewsFeed(Long userId) {
        User user = userRepository.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist."));
        Set<Long> userIds = user.getFollowing();
        userIds.add(userId);
        Set<Post> allPosts = postRepository.getAllPosts();
        return allPosts.stream()
                .filter(post -> userIds.contains(post.getUserId()))
                .limit(NEWS_FEED_LIMIT)
                .map(Post::getContent)
                .collect(Collectors.toList());

    }
}
