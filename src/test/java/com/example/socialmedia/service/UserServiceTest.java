package com.example.socialmedia.service;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.RequiredParameterMissingException;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.repository.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private UserService unit;

    @Before
    public void setup() {
        unit = new UserService(userRepository);
        unit.createUser("Andy", "andy123@abc.com");
        unit.createUser("Tom", "tom@abc.com");
        unit.createUser("Ivo", "ivo.12@abc.com");

    }

    @Test
    public void shouldFollowUser() {
        // Scenario 1 if user tries to follow same user
        User newUser = new User(12L, "Name", "Email");
        userRepository.createOrUpdateUser(newUser);
        User anotherUser = new User(13L, "Name", "Email");
        userRepository.createOrUpdateUser(anotherUser);
        unit.follow(12L, 13L);
        unit.follow(12L, 13L);
        assertThat(newUser.getFollowing()).isEqualTo(Collections.singleton(13L));

        // Scenario 2 user follows user , which was not followed
        User user = unit.follow(1L, 2L);
        assertThat(user.getFollowing()).isEqualTo(Collections.singleton(2L));
    }

    @Test
    public void shouldUnfollowUser() {
        // Scenario 1 if user tries to follow the use he/she does not
        User newUser = new User(12L, "Name", "Email");
        userRepository.createOrUpdateUser(newUser);
        User anotherUser = new User(13L, "Name", "Email");
        userRepository.createOrUpdateUser(anotherUser);
        unit.follow(12L, 13L);
        unit.unfollow(12L, 13L);

        // Scenario 2 unfollow the user which was not followed earlier.
        assertThat(newUser.getFollowing()).isNotEqualTo(Collections.singleton(2L));
        User user = unit.unfollow(1L, 2L);
        assertThat(user.getFollowing()).isNotEqualTo(Collections.singleton(2L));
    }

    @Test
    public void shouldThrow404Exception() {
        assertThatThrownBy(() -> unit.unfollow(5L, 5L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found");
    }

    @Test
    public void shouldThrowMissingRequiredParamException() {
        assertThatThrownBy(() -> unit.createUser(null, null))
                .isInstanceOf(RequiredParameterMissingException.class)
                .hasMessage("Required parameter missing.");
    }

}
