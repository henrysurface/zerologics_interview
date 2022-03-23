package com.interview.zerologics.demo.service;

import com.interview.zerologics.demo.entity.Follow;
import com.interview.zerologics.demo.entity.User;
import com.interview.zerologics.demo.repository.FollowRepository;
import com.interview.zerologics.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository fellowRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void followUser(Long fromUserId, Long toUserId) throws ChangeSetPersister.NotFoundException {
        Follow fellow = Follow.builder().fromUser(
                userRepository.findById(fromUserId).orElseThrow(ChangeSetPersister.NotFoundException::new))
                .toUser(userRepository.findById(toUserId).orElseThrow(ChangeSetPersister.NotFoundException::new))
                .build();
        fellowRepository.save(fellow);
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
