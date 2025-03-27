package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class JobDoesNotExistException extends RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(JobDoesNotExistException.class);

    public JobDoesNotExistException(String message) {

        super(message);
        logger.error(ExceptionMessages.JOB_DOES_NOT_EXIST.getMessage());

    }
}
