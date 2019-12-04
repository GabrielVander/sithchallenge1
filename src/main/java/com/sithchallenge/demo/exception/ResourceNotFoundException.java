package com.sithchallenge.demo.exception;

import com.sithchallenge.demo.controller.AuthorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private final Logger logger = Logger.getLogger(ResourceNotFoundException.class.getName());

    private final String defaultMessage = "Resource not found";

    public ResourceNotFoundException() {
        super();
        logger.log(Level.SEVERE, defaultMessage);
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        logger.log(Level.SEVERE, message);
    }
    public ResourceNotFoundException(String message) {
        super(message);
        logger.log(Level.SEVERE, message);
    }
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
        logger.log(Level.SEVERE, defaultMessage);
    }
}
