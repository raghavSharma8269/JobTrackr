package com.example.JobApplicationManager.service.resumeServices;

import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetFileNamesService {

    private final UserRepository userRepository;

    public GetFileNamesService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Map<String, String>> execute() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        CustomUser user = userRepository.findById(email).orElseThrow();

        Map<String, String> response = new HashMap<>();
        response.put("resumeFileName", user.getResumeFileName());
        response.put("cvFileName", user.getCoverLetterFileName());

        return ResponseEntity.ok(response);
    }
}
