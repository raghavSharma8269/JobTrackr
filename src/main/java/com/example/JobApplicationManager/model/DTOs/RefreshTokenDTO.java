package com.example.JobApplicationManager.model.DTOs;

import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.RefreshToken;
import lombok.Data;

import java.time.Instant;

@Data
public class RefreshTokenDTO {
    private String id;
    private String token;
    private Instant expiryDate;
    private CustomUser customUser;


    public RefreshTokenDTO(RefreshToken refreshToken) {
        this.id = refreshToken.getId();
        this.token = refreshToken.getToken();
        this.expiryDate = refreshToken.getExpiryDate();
        this.customUser = refreshToken.getCustomUser();
    }

}
