package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotValidException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(UserNotValidException.class);

    public EmailNotValidException(String message) {
        super(message);
        logger.error("Error " + getClass() + " thrown");
    }
}
