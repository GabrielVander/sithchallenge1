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

    public ResourceNotFoundException(String message) {
        super(message);
        logger.log(Level.SEVERE, message);
    }

}
