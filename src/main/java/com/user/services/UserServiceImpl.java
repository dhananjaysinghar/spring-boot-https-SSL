package com.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.models.User;
import com.user.repo.UserRepositoryImpl;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public User getUserDetails(String userId) {
        log.debug("Trying to get user data from user repo ");
        return userRepository.getUserDetails(userId);
    }
}
