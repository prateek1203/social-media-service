package com.example.socialmedia.service;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.RequiredParameterMissingException;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;

import java.util.Map;


public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String userName, String email) {
        if (Strings.isBlank(userName) || Strings.isBlank(email)) {
            throw new RequiredParameterMissingException("Required parameter missing.");
        }
        Map<Long, User> users = userRepository.getAllUsers();
        User user = users.isEmpty()
                ? new User(1L, userName, email)
                : new User((long) (users.size() + 1), userName, email);

        userRepository.createOrUpdateUser(user);
        System.out.println(user.getName() + " created");
        return user;
    }

    public User follow(Long followerId, Long followeeId) {
        User user = userRepository.getUserById(followerId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        User userToBeFollowed = userRepository.getUserById(followeeId)
                .orElseThrow(() -> new UserNotFoundException("followee does not exist"));

        System.out.printf("%s wants to follow %s \n", user.getName(), userToBeFollowed.getName());
        if (!user.getFollowing().contains(userToBeFollowed.getId())) {
            user.getFollowing().add(userToBeFollowed.getId());
            userRepository.createOrUpdateUser(user);
            System.out.printf("%s follows %s %n", user.getName(), userToBeFollowed.getName());
        } else {
            System.out.println(user.getName() + "already follows " + userToBeFollowed.getName());
        }
        return user;
    }

    public User unfollow(Long followerId, Long followeeId) {
        User user = userRepository.getUserById(followerId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        User userToBeUnfollowed = userRepository.getUserById(followeeId)
                .orElseThrow(() -> new UserNotFoundException("followee does not exist"));


        if (user.getFollowing().contains(userToBeUnfollowed.getId())) {
            user.getFollowing().remove(followeeId);
            userRepository.createOrUpdateUser(user);
            System.out.printf("%s unfollows %s %n", user.getName(), userToBeUnfollowed.getName());
        } else {
            System.out.println(user.getName() + " dose not follow " + userToBeUnfollowed.getName());
        }
        return user;
    }
}
