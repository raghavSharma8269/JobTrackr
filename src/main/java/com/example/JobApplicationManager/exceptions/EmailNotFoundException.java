package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends RuntimeException{

    private static final Logger logger = LoggerFactory.getLogger(EmailNotFoundException.class);
    public EmailNotFoundException(String message) {
        super(ExceptionMessages.EMAIL_NOT_FOUND.getMessage());
        logger.error("Error " + getClass() + " thrown");
    }
}
