package com.user.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum InternalStandardError {
    USER_NOT_EXIST("INVALID_INPUT", "User not exist", "4001", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("INTERNAL_ERROR", "", "5001", HttpStatus.INTERNAL_SERVER_ERROR);

    private String errorMessage;
    private String description;
    private String errorCode;
    private HttpStatus httpStatus;
}
