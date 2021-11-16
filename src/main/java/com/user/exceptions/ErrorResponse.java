package com.user.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class ErrorResponse {

    private String errorMessage;
    private String description;
    private String errorCode;
    private LocalDateTime timestamp;

    public ErrorResponse(InternalStandardError error, String... errorMessage) {
        this.errorMessage = errorMessage.length > 0 ? errorMessage[0] : error.getErrorMessage();
        this.description = error.getDescription();
        this.errorCode = error.getErrorCode();
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
    }
}
