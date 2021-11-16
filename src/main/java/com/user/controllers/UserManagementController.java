package com.user.controllers;

import com.user.models.User;
import com.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserManagementController {

    private final UserService service;

    @GetMapping(path = "/{userId}")
    public User getUserDetails(@PathVariable String userId) {
        log.debug("Request received to get user info by userId : {}", userId);
        return service.getUserDetails(userId);
    }

    @GetMapping
    public User getUser() {
        return new User(UUID.randomUUID().toString(), Arrays.asList("write", "read"));
    }
}
