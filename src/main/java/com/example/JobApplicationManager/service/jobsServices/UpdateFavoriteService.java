package com.example.JobApplicationManager.service.jobsServices;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.JobDoesNotExistException;
import com.example.JobApplicationManager.model.DTOs.JobsDTO;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.model.repositories.JobsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateFavoriteService {

    private final JobsRepository jobsRepository;
    private final Logger logger = LoggerFactory.getLogger(UpdateFavoriteService.class);


    public UpdateFavoriteService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public ResponseEntity<JobsDTO> execute(String id, boolean favorite){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        Optional<JobsList> optionalJob = jobsRepository.findById(id);

        logger.info("Executing " + getClass() + " input: " + id + ", favorite: " + favorite);

        if(optionalJob.isPresent() && optionalJob.get().getCustomUser().getEmail().equals(email)){
            JobsList job = optionalJob.get();
            job.setFavorite(favorite);
            jobsRepository.save(job);
            return ResponseEntity.ok().body(new JobsDTO(job));
        } else {
            throw new JobDoesNotExistException(ExceptionMessages.JOB_DOES_NOT_EXIST.getMessage());
        }
    }

}
