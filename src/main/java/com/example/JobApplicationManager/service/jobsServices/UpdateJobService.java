package com.example.JobApplicationManager.service.jobsServices;

import com.example.JobApplicationManager.Command;
import com.example.JobApplicationManager.UpdateJobCommand;
import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.JobDoesNotExistException;
import com.example.JobApplicationManager.exceptions.NotAuthorizedToEditJobException;
import com.example.JobApplicationManager.exceptions.validator.JobValidator;
import com.example.JobApplicationManager.model.DTOs.JobsDTO;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.model.repositories.JobsRepository;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateJobService implements Command<UpdateJobCommand, JobsDTO> {

    private final JobsRepository jobsRepository;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UpdateJobService.class);

    public UpdateJobService(JobsRepository jobsRepository, UserRepository userRepository) {
        this.jobsRepository = jobsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<JobsDTO> execute(UpdateJobCommand command) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        logger.info("Executing " + getClass() + " input: " + command);

        Optional<JobsList> existingJob = jobsRepository.findById(command.getId());
        if (existingJob.isPresent()) {
            JobsList jobToUpdate = existingJob.get();
            if (jobToUpdate.getCustomUser().getEmail().equals(email)) {
                JobsList job = command.getJob();
                job.setId(command.getId());
                Optional<CustomUser> customUser = userRepository.findById(email);
                customUser.ifPresent(job::setCustomUser);
                JobValidator.execute(job);
                jobsRepository.save(job);
                return ResponseEntity.ok().body(new JobsDTO(job));
            } else {
                throw new NotAuthorizedToEditJobException(ExceptionMessages.NOT_AUTHORIZED_TO_EDIT_JOB.getMessage());
            }
        } else {
            throw new JobDoesNotExistException(ExceptionMessages.JOB_DOES_NOT_EXIST.getMessage());
        }
    }
}