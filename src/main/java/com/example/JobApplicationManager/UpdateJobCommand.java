package com.example.JobApplicationManager;

import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.JobsList;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateJobCommand {

    private String id;
    private JobsList job;

    public UpdateJobCommand(String id, JobsList job) {
        this.id = id;
        this.job = job;
    }
}
