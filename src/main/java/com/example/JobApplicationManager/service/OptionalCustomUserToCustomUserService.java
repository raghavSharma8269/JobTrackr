package com.example.JobApplicationManager.service;

import com.example.JobApplicationManager.exceptions.EmailNotFoundException;
import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OptionalCustomUserToCustomUserService {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(OptionalCustomUserToCustomUserService.class);

    public OptionalCustomUserToCustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomUser execute (String email){

        logger.info(" Executing " + getClass() + " || trying to find custom user by email: " + email);

        Optional<CustomUser> optionalCustomUser = userRepository.findById(email);

        if (optionalCustomUser.isPresent()){
            return optionalCustomUser.get();
        }

        else {
            throw new EmailNotFoundException(ExceptionMessages.EMAIL_NOT_FOUND.getMessage());
        }

    }

}
