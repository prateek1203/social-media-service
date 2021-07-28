package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    private final PostRepository postRepository = new PostRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Test
    public void shouldPersistPost() throws Exception {
        Post post = new Post(1L, 1L, "Test Content here...");
        postRepository.createOrUpdatePost(post);
        assertThat(postRepository.getPostById(1L)).isNotNull();

        postRepository.getPostById(1L).ifPresent((post1) ->
                assertThat(post1.getUserId()).isEqualTo(1L)
        );

        postRepository.getPostById(1L).ifPresent((post2) ->
                assertThat(post2.getContent()).isEqualTo("Test Content here...")
        );
    }

    @Test
    public void shouldGetAllPosts() throws InterruptedException {
        User user = new User(1L, "Name", "Email");
        userRepository.createOrUpdateUser(user);
        Post first = new Post(3L, user.getId(), "Test Content here " + LocalDateTime.now());
        postRepository.createOrUpdatePost(first);
        Thread.sleep(10);
        Post second = new Post(4L, user.getId(), "Test Content here " + LocalDateTime.now());
        postRepository.createOrUpdatePost(second);
        Thread.sleep(20);
        Post third = new Post(5L, user.getId(), "Test Content here " + LocalDateTime.now());
        postRepository.createOrUpdatePost(third);
        Thread.sleep(5);

        assertThat(postRepository.getAllPosts().size()).isEqualTo(3);
    }

}
