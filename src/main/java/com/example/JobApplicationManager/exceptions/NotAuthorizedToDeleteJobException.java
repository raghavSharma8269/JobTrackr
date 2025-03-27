package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorizedToDeleteJobException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(NotAuthorizedToDeleteJobException.class);

    public NotAuthorizedToDeleteJobException(String message) {
        super(message);
        logger.error("Throwing " + getClass() + " exception");

    }
}
