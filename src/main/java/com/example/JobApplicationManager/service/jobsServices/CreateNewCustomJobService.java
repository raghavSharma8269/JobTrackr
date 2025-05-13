package com.example.JobApplicationManager.service.jobsServices;

import com.example.JobApplicationManager.exceptions.EmailNotValidException;
import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.validator.JobValidator;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.model.repositories.JobsRepository;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CreateNewCustomJobService {

    private final Logger logger = LoggerFactory.getLogger(CreateNewCustomJobService.class);
    private final JobsRepository jobsRepository;
    private final UserRepository userRepository;

    public CreateNewCustomJobService(JobsRepository jobsRepository, UserRepository userRepository) {
        this.jobsRepository = jobsRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> execute(JobsList job) {


        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        logger.info("Executing " + getClass() + " job input: " + job);
        logger.info("Adding to " + email + " jobs list");



        Optional<CustomUser> optionalCustomUser = userRepository.findById(email);

        if (optionalCustomUser.isPresent()){
            job.setCustomUser(optionalCustomUser.get());
        }
        else {
            throw new EmailNotValidException(ExceptionMessages.EMAIL_NOT_FOUND.getMessage());
        }

        job.setLocalDateTime(LocalDateTime.now());

        JobValidator.execute(job);

        jobsRepository.save(job);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Job Added Successfully");
    }
}
