package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(UnauthorizedException.class);

    public UnauthorizedException(String message) {
        super(message);
        logger.error(getClass() + " thrown");
    }
}
