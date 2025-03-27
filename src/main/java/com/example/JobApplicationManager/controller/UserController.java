package com.example.JobApplicationManager.controller;

import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.service.authServices.CreateNewUserService;
import com.example.JobApplicationManager.service.authServices.LoginService;
import com.example.JobApplicationManager.service.emailVerification.VerifyAccountService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class UserController {

private final CreateNewUserService createNewUserService;
private final LoginService loginService;
private final VerifyAccountService verifyAccountService;
    public UserController(
            CreateNewUserService createNewUserService,
            LoginService loginService, VerifyAccountService verifyAccountService
    ) {
        this.createNewUserService = createNewUserService;
        this.loginService = loginService;
        this.verifyAccountService = verifyAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser customUser) throws MessagingException {
        return createNewUserService.execute(customUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomUser user) {
        String jwtToken = loginService.authenticateAndGenerateToken(user);
        return ResponseEntity.ok(jwtToken);
    }


    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String emailVerificationToken) {
        return verifyAccountService.execute(emailVerificationToken);
    }

}
