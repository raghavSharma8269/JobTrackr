package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotAuthorizedException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(NotAuthorizedException.class);

    public NotAuthorizedException(String message) {
        super(message);
        logger.error(getClass() + " thrown");
    }
}
