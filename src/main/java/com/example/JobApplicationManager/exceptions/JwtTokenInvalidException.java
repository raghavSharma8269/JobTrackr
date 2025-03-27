package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JwtTokenInvalidException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(JwtTokenInvalidException.class);

    public JwtTokenInvalidException(String message) {

        super(message);
        logger.error("Throwing " + getClass() + " exception");
    }
}
