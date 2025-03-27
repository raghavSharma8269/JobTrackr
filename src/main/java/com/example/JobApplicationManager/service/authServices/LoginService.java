package com.example.JobApplicationManager.service.authServices;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import com.example.JobApplicationManager.security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AuthenticationManager manager;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public LoginService(AuthenticationManager manager, UserRepository userRepository) {
        this.manager = manager;
        this.userRepository = userRepository;
    }

    public String authenticateAndGenerateToken(CustomUser user) {

        logger.info("Executing " + getClass() + " ,authenticating " + user.getEmail());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );

        Authentication authentication = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtUtil.generateToken((User) authentication.getPrincipal());

        CustomUser authenticatedUser = userRepository.findById(user.getEmail()).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.EMAIL_NOT_FOUND.getMessage()));
        authenticatedUser.setAuthToken(jwt);
        userRepository.save(authenticatedUser);

        return jwt;
    }
}