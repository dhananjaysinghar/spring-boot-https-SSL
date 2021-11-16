package com.user.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.user.models.User;
import com.user.utils.HttpClientLogInterceptor;

@Service
@Slf4j
public class DBServiceImpl implements DBService {

    private final RestTemplate restTemplate;

    public DBServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.restTemplate.getInterceptors().add(new HttpClientLogInterceptor());
    }

    @Override
    public User getUserDetails(String userId) {
        return restTemplate.getForObject("https://user-management.com:8443/users", User.class);
    }
}
