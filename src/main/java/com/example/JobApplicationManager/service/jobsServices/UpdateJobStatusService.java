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
public class UpdateJobStatusService {
    private final JobsRepository jobsRepository;
    private final Logger logger = LoggerFactory.getLogger(UpdateJobStatusService.class);

    public UpdateJobStatusService(
            JobsRepository jobsRepository
    ) {
        this.jobsRepository = jobsRepository;
    }

    public ResponseEntity<JobsDTO> execute(String jobId, String status) throws Exception {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        logger.info("Executing " + getClass() + " input: " + jobId + ", status: " + status);

        Optional<JobsList> optionalJob = jobsRepository.findById(jobId);

        if(optionalJob.isPresent() && optionalJob.get().getCustomUser().getEmail().equals(email)){
            JobsList job = optionalJob.get();

            if(status == null || status.isEmpty()) {
                job.setApplicationStatus(null);
            }
            else{
                job.setApplicationStatus(ApplicationStatus.valueOf(status));

            }

            jobsRepository.save(job);
            return ResponseEntity.ok().body(new JobsDTO(job));
        } else {
            throw new JobDoesNotExistException(ExceptionMessages.JOB_DOES_NOT_EXIST.getMessage());

        }


    }

}
