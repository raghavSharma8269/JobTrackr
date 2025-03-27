package com.example.JobApplicationManager.model.entity;

import com.example.JobApplicationManager.security.Authority;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class CustomUser {


    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "resume", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] resume;

    @Column(name = "cover_letter", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] coverLetter;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "num_of_ai_requests", nullable = false)
    private int numOfAiRequests;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;

    @Column(name = "email_verification_token")
    private String emailVerificationToken;
}
