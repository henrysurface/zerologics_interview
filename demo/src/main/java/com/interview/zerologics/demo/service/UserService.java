package com.interview.zerologics.demo.service;

import com.interview.zerologics.demo.entity.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

public interface UserService {
    void createUser(User user);

    void followUser(Long fromUser, Long toUser) throws ChangeSetPersister.NotFoundException;

    Optional<User> findUserById(Long userId);
}
