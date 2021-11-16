package com.user.exceptions;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {

    private final InternalStandardError error;

    public UserServiceException(InternalStandardError error) {
        super(error.getErrorMessage());
        this.error = error;
    }
}
