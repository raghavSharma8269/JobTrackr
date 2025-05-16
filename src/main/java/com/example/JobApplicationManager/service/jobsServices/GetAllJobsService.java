package com.example.JobApplicationManager.service.jobsServices;

import com.example.JobApplicationManager.exceptions.EmailNotValidException;
import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.model.DTOs.JobsDTO;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.model.repositories.JobsRepository;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetAllJobsService  {

    private final JobsRepository jobsRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(GetAllJobsService.class);

    public GetAllJobsService(JobsRepository jobsRepository, UserRepository userRepository) {
        this.jobsRepository = jobsRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<List<JobsDTO>> execute(Object sortBy, String search, ApplicationStatus status) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        logger.info("Executing " + getClass() + ", getting all jobs saved by: " + email);

        Sort sort = Sort.by(Sort.Direction.DESC, "localDateTime"); // sorts by newest job first by default
        if ("localDateTime".equalsIgnoreCase(sortBy.toString())) {
            sort = Sort.by(Sort.Direction.DESC, "localDateTime");
        } else if ("companyName".equalsIgnoreCase(sortBy.toString())) {
            sort = Sort.by(Sort.Direction.ASC, "companyName");
        } else if ("jobTitle".equalsIgnoreCase(sortBy.toString())) {
            sort = Sort.by(Sort.Direction.ASC, "jobTitle");
        } else if ("favorite".equalsIgnoreCase(sortBy.toString())) {
            sort = Sort.by(Sort.Direction.DESC, "favorite");
        }


        Optional<CustomUser> optionalCustomUser = userRepository.findById(email);

        
        //Default (with no search parameters) is sorted by newest job first
        if (optionalCustomUser.isPresent()){
            CustomUser customUser = optionalCustomUser.get();
            List<JobsList> jobs = jobsRepository.search(customUser.getEmail(), search, status, sort);

            List<JobsDTO> jobsDTOS = jobs
                    .stream()
                    .map(JobsDTO::new)
                    .toList();

            return ResponseEntity.ok().body(jobsDTOS);
        }
        else {
            throw new EmailNotValidException(ExceptionMessages.EMAIL_NOT_FOUND.getMessage());
        }

    }
}
