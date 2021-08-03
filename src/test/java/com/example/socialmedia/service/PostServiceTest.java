package com.example.socialmedia.service;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.RequiredParameterMissingException;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.PostRepositoryImpl;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.repository.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PostServiceTest {

    private final PostRepository postRepository = new PostRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private PostService postService;

    @Before
    public void setup() throws InterruptedException {
        postService = new PostService(userRepository, postRepository);
        UserService unit = new UserService(userRepository);
        unit.createUser("Andy", "andy123@abc.com");
        unit.createUser("Tom", "tom@abc.com");
        unit.createUser("Ivo", "ivo.12@abc.com");

        unit.follow(1L, 2L);
        createPostForUsers();
    }

    private void createPostForUsers() throws InterruptedException {

        for (int i = 1; i <= 10; i++) {
            postService.createPost(1L, "Andy's post" + LocalDateTime.now());
            Thread.sleep(100);
            postService.createPost(2L, "Tom's post" + LocalDateTime.now());
            Thread.sleep(200);
            postService.createPost(3L, "Hey, this is post for Ivo." + LocalDateTime.now());
            Thread.sleep(300);
        }

    }

    @Test(timeout = 2000)
    public void shouldGetLatestTwentyFeeds() {
        List<String> newsFeedVisible = postService.getNewsFeed(1L);
        assertThat(newsFeedVisible.get(0)).contains("Tom's post");
        assertThat(newsFeedVisible.get(1)).contains("Andy's post");
        assertThat(newsFeedVisible.size()).isEqualTo(20);
    }

    @Test
    public void shouldAddPostToUser() {
        Optional<User> user = userRepository.getUserById(1L);
        user.ifPresent(value -> assertThat(value.getPostIds()).contains(1L));
    }

    @Test
    public void shouldThrow404Exception() {
        assertThatThrownBy(() -> postService.createPostForUser(5L, 5L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found");
    }

    @Test
    public void shouldThrowMissingRequiredParamException() {
        assertThatThrownBy(() -> postService.createPost(5L, null))
                .isInstanceOf(RequiredParameterMissingException.class)
                .hasMessage("Blank Post can not be created");
    }

}
