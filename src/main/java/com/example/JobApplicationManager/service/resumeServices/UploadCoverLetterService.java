package com.example.JobApplicationManager.service.resumeServices;

import com.example.JobApplicationManager.exceptions.EmailNotFoundException;
import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.IncorrectFileTypeException;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UploadCoverLetterService {

    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UploadCoverLetterService.class);

    public UploadCoverLetterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> execute(MultipartFile coverLetter) throws IOException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        logger.info("Executing " + getClass());
        logger.info("Adding cv to " + email);

        Optional<CustomUser> optionalCustomUser = userRepository.findById(email);

        if (optionalCustomUser.isPresent()){
            CustomUser customUser = optionalCustomUser.get();

            if (coverLetter.getContentType().equals("application/pdf")){
                customUser.setCoverLetter(coverLetter.getBytes());
                userRepository.save(customUser);
            }

            else {
                throw new IncorrectFileTypeException(ExceptionMessages.COVER_LETTER_MUST_BE_PDF.getMessage());
            }
        }

        else {
            throw new EmailNotFoundException(ExceptionMessages.EMAIL_NOT_FOUND.getMessage());
        }

        return ResponseEntity.ok().body("Cover Letter Uploaded Successfully");
    }
}
