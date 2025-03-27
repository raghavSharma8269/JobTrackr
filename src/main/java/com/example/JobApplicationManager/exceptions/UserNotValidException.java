package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotValidException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(UserNotValidException.class);

    public UserNotValidException(String message){
        super(message);
        logger.error("Exception " + UserNotValidException.class.getSimpleName() + " thrown");
    }
}
