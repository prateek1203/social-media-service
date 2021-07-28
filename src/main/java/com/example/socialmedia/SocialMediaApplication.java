package com.example.socialmedia;

import com.example.socialmedia.util.SocialMediaUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SocialMediaApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext app = SpringApplication.run(SocialMediaApplication.class, args);
        SocialMediaUtility.initializeServices(app);
        SocialMediaUtility.loadInitialData();
    }
}
