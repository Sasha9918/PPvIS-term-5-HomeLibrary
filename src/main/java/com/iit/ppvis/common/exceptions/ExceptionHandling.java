package com.iit.ppvis.common.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(EntityNotFoundException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), e.getStatus(), request);
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    protected ResponseEntity<Object> handleForbiddenAccessException(ForbiddenAccessException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), e.getStatus(), request);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    protected ResponseEntity<Object> handleExistedException(EntityAlreadyExistsException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), e.getStatus(), request);
    }

}
