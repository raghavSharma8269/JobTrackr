package com.example.JobApplicationManager.service.resumeServices;

import com.example.JobApplicationManager.model.repositories.JobsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetResumeFeedbackService {

    public final JobsRepository jobsRepository;

    public GetResumeFeedbackService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public ResponseEntity<String> execute(String jobId) {
        return ResponseEntity.ok(jobsRepository.findById(jobId).get().getResumeFeedback());
    }
}
