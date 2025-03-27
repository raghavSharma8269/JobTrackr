package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JobNotValidException extends RuntimeException{

    private final Logger logger = LoggerFactory.getLogger(JobNotValidException.class);

    public JobNotValidException(String message){
        super(message);
        logger.error("Throwing " + getClass() + " Exception");
    }

}
