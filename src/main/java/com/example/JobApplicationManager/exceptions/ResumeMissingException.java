package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResumeMissingException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(ResumeMissingException.class);
    public ResumeMissingException(String message) {
        super(message);
        logger.error("Throwing " + getClass() + " Exception");
    }
}
