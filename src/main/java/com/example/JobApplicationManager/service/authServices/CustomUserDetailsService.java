package com.example.JobApplicationManager.service.authServices;

import com.example.JobApplicationManager.exceptions.EmailNotFoundException;
import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        
        log.info("Executing " + getClass() + " input: " + email);

        CustomUser user = userRepository.findById(email)
            .orElseThrow(() -> new EmailNotFoundException(ExceptionMessages.EMAIL_NOT_FOUND.getMessage()));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .authorities(user.getAuthority().name())
            .build();
    }
}