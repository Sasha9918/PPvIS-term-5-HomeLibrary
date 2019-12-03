package com.iit.ppvis.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class EntityAlreadyExistsException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public EntityAlreadyExistsException(String message) {
        super(message);
        this.status = UNPROCESSABLE_ENTITY;
    }

}
