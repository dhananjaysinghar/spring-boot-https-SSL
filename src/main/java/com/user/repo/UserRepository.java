package com.user.repo;

import com.user.models.User;

public interface UserRepository {
    User getUserDetails(String userId);
}
