package com.example.socialmedia.repository;

import com.example.socialmedia.exception.UserNotFoundException;
import com.example.socialmedia.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final Map<Long, User> usersMap = new HashMap<>();

    @Override
    public Map<Long, User> getAllUsers() { return usersMap; }

    @Override
    public void createOrUpdateUser(User user) {
        usersMap.put(user.getId(),user);
    }

    @Override
    public Optional<User> getUserById(Long userId) throws RuntimeException{
        if ( ! usersMap.containsKey(userId))
            throw new UserNotFoundException("User not found");
        return Optional.ofNullable(usersMap.get(userId));
    }
}
