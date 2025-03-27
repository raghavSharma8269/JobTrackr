package com.example.JobApplicationManager.exceptions;

import com.example.JobApplicationManager.service.emailVerification.EmailVerificationTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailVerificationException extends RuntimeException {
    private final Logger logger = LoggerFactory.getLogger(EmailVerificationTokenGenerator.class);
    public EmailVerificationException(String message) {
        super(message);
        logger.error("Exception " +getClass() + " thrown");
    }
}
