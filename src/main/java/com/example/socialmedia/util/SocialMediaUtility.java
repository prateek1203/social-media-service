package com.example.socialmedia.util;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.PostService;
import com.example.socialmedia.service.UserService;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class SocialMediaUtility {

    private static UserService userService;
    private static PostService postService;

    public static void loadInitialData() throws InterruptedException {
        System.out.println("Starting server with initial data:- ");

        User andy = userService.createUser("Andy", "andy123@abc.com");
        User tom = userService.createUser("Tom", "tom@abc.com");
        User ivo = userService.createUser("Ivo", "ivo.12@abc.com");

        System.out.println("**********************************");

        System.out.println("Creating default posts for all users");
        for (int i = 1; i <= 10; i++) {
            postService.createPost(andy.getId(), "===Andy's post=== " + LocalDateTime.now());
            Thread.sleep(200);
            postService.createPost(tom.getId(), "===Tom's post=== " + LocalDateTime.now());
            Thread.sleep(400);
            postService.createPost(ivo.getId(), "===Hey, this is post for Ivo.=== " + LocalDateTime.now());
            Thread.sleep(600);
        }

        System.out.println("**********************************");

        userService.follow(andy.getId(), tom.getId());
        userService.follow(andy.getId(), ivo.getId());
        showUserNewsFeed(andy);

        System.out.println("*************** After unfollow Tom ****************");
        userService.unfollow(andy.getId(), tom.getId());
        showUserNewsFeed(andy);
    }

    private static void showUserNewsFeed(User user) throws InterruptedException {
        List<String> newsFeedForAndy = postService.getNewsFeed(user.getId());
        System.out.printf("************* Showing %s recent NEWS feeds for %s ************* \n"
                , newsFeedForAndy.size(), user.getName());
        Thread.sleep(1000);
        newsFeedForAndy.forEach(System.out::println);
    }

    public static void initializeServices(ApplicationContext app) {
        userService = new UserService(app.getBean(UserRepository.class));
        postService = new PostService(app.getBean(UserRepository.class), app.getBean(PostRepository.class));
    }

}
