package com.example.JobApplicationManager.exceptions.validator;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.UserNotValidException;
import com.example.JobApplicationManager.model.entity.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserValidator {
    private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

    public static void execute(CustomUser customUser){
        logger.info("Executing " + UserValidator.class.getSimpleName() + " input " + customUser);

        if (customUser.getEmail().isEmpty()){
            throw new UserNotValidException(ExceptionMessages.EMAIL_REQUIRED.getMessage());
        }

        if (customUser.getPassword().isEmpty()){
            throw new UserNotValidException(ExceptionMessages.PASSWORD_REQUIRED.getMessage());
        }

        if (customUser.getFullName().isEmpty()){
            throw new UserNotValidException(ExceptionMessages.FULL_NAME_REQUIRED.getMessage());
        }
    }
}
