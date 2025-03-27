package com.example.JobApplicationManager.service.userServices;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.ResetPasswordException;
import com.example.JobApplicationManager.model.DTOs.ResetPasswordDTO;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import com.example.JobApplicationManager.service.OptionalCustomUserToCustomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService;
    private final Logger logger = LoggerFactory.getLogger(ResetPasswordService.class);

    public ResetPasswordService(UserRepository userRepository,
                                PasswordEncoder passwordEncoder,
                                OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.optionalCustomUserToCustomUserService = optionalCustomUserToCustomUserService;
    }

    public ResponseEntity<String> execute (ResetPasswordDTO request) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        CustomUser user = optionalCustomUserToCustomUserService.execute(email);

        logger.info("Executing password reset for " + email);

        String originalPassword = request.getOriginalPassword();
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();

        if (passwordEncoder.matches(originalPassword, user.getPassword())){
            if (newPassword.equals(confirmPassword)) {
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userRepository.save(user);
                return ResponseEntity.ok().body("Password changed successfully");
            } else {
                throw new ResetPasswordException(ExceptionMessages.PASSWORD_MISMATCH.getMessage());
            }
        } else {
            throw new ResetPasswordException(ExceptionMessages.ORIGINAL_PASSWORD_IS_WRONG.getMessage());
        }
    }
}
