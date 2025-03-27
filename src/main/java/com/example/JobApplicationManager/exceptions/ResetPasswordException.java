package com.example.JobApplicationManager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResetPasswordException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(ResetPasswordException.class);
    public ResetPasswordException(String message) {
        super(message);
        logger.error(getClass() + " message: " + message);
    }
}
