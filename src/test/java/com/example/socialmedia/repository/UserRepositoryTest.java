package com.example.socialmedia.repository;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.repository.UserRepositoryImpl;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Test
    public void shouldPersistUser() {
        User user = new User(1L,"Andy", "andyh12@abc.com");
        userRepository.createOrUpdateUser(user);
        assertThat(userRepository.getUserById(1L)).isNotNull();
        userRepository.getUserById(1L).ifPresent((user1 -> assertThat(user1.getName()).isEqualTo("Andy")));

    }

    @Test
    public void shouldGetAllUsers() {
        User user1 = new User(1L,"Andy","andyh12@abc.com");
        userRepository.createOrUpdateUser(user1);
        User user2 = new User(2L,"Tom","tom.d.12@abc.com");
        userRepository.createOrUpdateUser(user2);

        assertThat(userRepository.getAllUsers().size()).isEqualTo(2);
    }

    @Test
    public void shouldThrowExceptionIfUserDoesNotExist(){
        User user1 = new User(1L,"Andy","andyh12@abc.com");
        userRepository.createOrUpdateUser(user1);
        assertThatThrownBy(() -> userRepository.getUserById(2L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found");
    }
}
