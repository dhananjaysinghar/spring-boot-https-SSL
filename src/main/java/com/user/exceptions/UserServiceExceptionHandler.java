package com.user.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class UserServiceExceptionHandler {

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorResponse> handleUserServiceException(UserServiceException exception) {
        InternalStandardError error = exception.getError();
        log.error("Error Occurred {} : {}", exception.getMessage(), error.getDescription());
        return new ResponseEntity<>(new ErrorResponse(error), error.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception) {
        InternalStandardError error = InternalStandardError.INTERNAL_SERVER_ERROR;
        log.error("Error Occurred : {}", exception.getMessage());
        log.error("Error stacktrace : {}", Arrays.deepToString(exception.getStackTrace()));
        return new ErrorResponse(error, exception.getMessage());
    }
}
