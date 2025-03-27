package com.example.JobApplicationManager.service.emailVerification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class EmailVerificationTokenGenerator {
    Logger logger = LoggerFactory.getLogger(EmailVerificationTokenGenerator.class);

    public String execute() {
        logger.info("Generating email verification token");
        return UUID.randomUUID().toString();
    }

}
