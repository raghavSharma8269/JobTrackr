package com.example.JobApplicationManager.service;

import com.example.JobApplicationManager.model.entity.RefreshToken;
import com.example.JobApplicationManager.model.repositories.RefreshTokensRepository;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokensRepository refreshTokensRepository;
    private final UserRepository userRepository;
    private final OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService;

    public RefreshTokenService(RefreshTokensRepository refreshTokensRepository, UserRepository userRepository, OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService) {
        this.refreshTokensRepository = refreshTokensRepository;
        this.userRepository = userRepository;
        this.optionalCustomUserToCustomUserService = optionalCustomUserToCustomUserService;
    }

    public RefreshToken execute (String email){

        RefreshToken refreshToken =  RefreshToken.builder()
                .customUser(optionalCustomUserToCustomUserService.execute(email))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(60000))
                .build();

        return refreshTokensRepository.save(refreshToken);


    }
}
