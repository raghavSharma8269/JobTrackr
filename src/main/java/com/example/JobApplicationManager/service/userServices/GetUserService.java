package com.example.JobApplicationManager.service.userServices;

import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.service.OptionalCustomUserToCustomUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {

    private final OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService;

    public GetUserService(
            OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService
    ) {
        this.optionalCustomUserToCustomUserService = optionalCustomUserToCustomUserService;
    }

    public ResponseEntity<CustomUser> execute (){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        CustomUser user = optionalCustomUserToCustomUserService.execute(email);
        return ResponseEntity.ok(user);
    }

}
