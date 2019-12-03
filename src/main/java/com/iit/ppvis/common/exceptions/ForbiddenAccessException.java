package com.iit.ppvis.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class ForbiddenAccessException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public ForbiddenAccessException(String message) {
        super(message);
        this.status = FORBIDDEN;
    }

}
