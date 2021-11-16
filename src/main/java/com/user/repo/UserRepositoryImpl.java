package com.user.repo;

import com.user.services.DBService;
import com.user.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.user.models.User;

import java.time.Duration;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @Value("${spring.cache.redis.time-to-live:PT24H}")
    private Duration timeToLive;

    private final RedisTemplate<String, User> redisUserTemplate;

    private final DBService dbService;

    public User getUserDetails(String userId) {
        String key = Constants.USER_HASH_KEY + userId;
        Optional<User> userOptional = Optional.empty();
        try {
            log.info("Trying to get user data from redis cache for id : {} with key : {}", userId, key);
            userOptional = Optional.ofNullable(redisUserTemplate.opsForValue().get(key));
        } catch (RuntimeException ex) {
            log.error("Failed to get data from redis cache : {}", ex.getMessage());
        }
        return userOptional.orElseGet(() -> getUserFromDBService(userId));
    }

    private User getUserFromDBService(String userId) {
        String key = Constants.USER_HASH_KEY + userId;
        log.info("Getting data from DBService for id : {}", userId);

        User user = dbService.getUserDetails(userId);
        try {
            log.info("Storing user info in redis cache for id : {} with key : {}", userId, key);
            redisUserTemplate.opsForValue().set(key, user, timeToLive);
        } catch (RuntimeException exception) {
            log.error("Failed to store data in cache : {}", exception.getMessage());
        }
        return user;
    }
}
