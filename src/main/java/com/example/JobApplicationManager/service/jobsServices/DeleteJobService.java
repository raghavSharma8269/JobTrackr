package com.example.JobApplicationManager.service.jobsServices;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.JobDoesNotExistException;
import com.example.JobApplicationManager.exceptions.NotAuthorizedToDeleteJobException;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.model.repositories.JobsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class DeleteJobService {

    private final JobsRepository jobsRepository;
    private final Logger logger = LoggerFactory.getLogger(DeleteJobService.class);

    public DeleteJobService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public ResponseEntity<String> execute(String id) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        logger.info("Executing " + getClass());
        logger.info("user: " + email + " is deleting job with id: " + id);

        if(jobsRepository.findById(id).isPresent()) {

            JobsList job = jobsRepository.findById(id).get();

            if(job.getCustomUser().getEmail().equals(email)) {
                jobsRepository.deleteById(id);
                return ResponseEntity.ok("Deleted job successfully.");
            }
            else {
                throw new NotAuthorizedToDeleteJobException(ExceptionMessages.NOT_AUTHORIZED_TO_DELETE_JOB.getMessage());
            }

        }

        else {
            throw new JobDoesNotExistException(ExceptionMessages.JOB_DOES_NOT_EXIST.getMessage());
        }

    }

}
