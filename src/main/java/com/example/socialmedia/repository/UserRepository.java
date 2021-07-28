package com.example.socialmedia.repository;

import com.example.socialmedia.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository {

    Map<Long, User> getAllUsers();

    void createOrUpdateUser(User user);

    Optional<User> getUserById(Long userId);

}
