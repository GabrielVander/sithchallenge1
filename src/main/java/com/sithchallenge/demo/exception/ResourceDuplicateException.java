package com.sithchallenge.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceDuplicateException extends RuntimeException {

    private final String defaultMessage = "Resource already on database";

    public ResourceDuplicateException(String message) {
        super(message);
        Logger logger = Logger.getLogger(ResourceNotFoundException.class.getName());
        logger.log(Level.SEVERE, message);
    }
}
