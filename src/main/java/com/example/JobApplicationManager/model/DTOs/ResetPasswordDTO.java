package com.example.JobApplicationManager.model.DTOs;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String originalPassword;
    private String newPassword;
    private String confirmPassword;
}
