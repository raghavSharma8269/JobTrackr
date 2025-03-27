package com.example.JobApplicationManager.service.emailVerification;

import com.example.JobApplicationManager.exceptions.EmailVerificationException;
import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifyAccountService {

    private final Logger logger = LoggerFactory.getLogger(VerifyAccountService.class);

    private final UserRepository userRepository;

    public VerifyAccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> execute(String emailVerificationToken){

        logger.info("Verifying email verification token: " + emailVerificationToken);

        Optional<CustomUser> optionalCustomUser = userRepository.findByEmailVerificationToken(emailVerificationToken);

        if(optionalCustomUser.isPresent()){
            CustomUser customUser = optionalCustomUser.get();
            customUser.setEmailVerified(true);
            userRepository.save(customUser);
            return ResponseEntity.ok("Successfully verified user");
        }
        else{
            throw new EmailVerificationException(ExceptionMessages.EMAIL_VERIFICATION_FAILED.getMessage());
        }


    }

}
