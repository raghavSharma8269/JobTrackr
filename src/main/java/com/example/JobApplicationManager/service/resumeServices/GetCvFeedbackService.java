package com.example.JobApplicationManager.service.resumeServices;

import com.example.JobApplicationManager.model.repositories.JobsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetCvFeedbackService {
    private final JobsRepository jobsRepository;

    public GetCvFeedbackService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public ResponseEntity<String> execute(String jobId) {
        return ResponseEntity.ok(jobsRepository.findById(jobId).get().getCvFeedback());
    }
}
