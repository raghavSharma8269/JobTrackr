package com.example.JobApplicationManager.model.DTOs;

import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.service.jobsServices.ApplicationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobsDTO {
    private String id;
    private String companyName;
    private String jobTitle;
    private String jobLocation;
    private String jobSalary;
    private String jobDescription;
    private LocalDateTime localDateTime;
    private ApplicationStatus applicationStatus;
    private String jobUrl;
    private boolean favorite;
    private String resumeFeedback;
    private String cvFeedback;


    public JobsDTO(JobsList jobsList) {
        this.id = jobsList.getId();
        this.companyName = jobsList.getCompanyName();
        this.jobTitle = jobsList.getJobTitle();
        this.jobLocation = jobsList.getJobLocation();
        this.jobSalary = jobsList.getJobSalary();
        this.jobDescription = jobsList.getJobDescription();
        this.localDateTime = jobsList.getLocalDateTime();
        this.applicationStatus = jobsList.getApplicationStatus();
        this.jobUrl = jobsList.getJobUrl();
        this.favorite = jobsList.isFavorite();
        this.resumeFeedback = jobsList.getResumeFeedback();
        this.cvFeedback = jobsList.getCvFeedback();
    }
}
