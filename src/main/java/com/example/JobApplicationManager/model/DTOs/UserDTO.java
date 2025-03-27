package com.example.JobApplicationManager.model.DTOs;

import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.security.Authority;
import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String fullName;
    private byte[] coverLetter;
    private byte[] resume;
    private Authority authority;
    private String authToken;

    public UserDTO(CustomUser customUser){
        this.email = customUser.getEmail();
        this.password = customUser.getPassword();
        this.fullName = customUser.getFullName();
        this.resume = customUser.getResume();
        this.coverLetter = customUser.getCoverLetter();
        this.authority = customUser.getAuthority();
        this.authToken = customUser.getAuthToken();
    }

}
