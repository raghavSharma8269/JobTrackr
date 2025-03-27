package com.example.JobApplicationManager.exceptions.validator;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.JobNotValidException;
import com.example.JobApplicationManager.model.entity.JobsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobValidator {
    private static final Logger logger = LoggerFactory.getLogger(JobValidator.class);

    public static void execute (JobsList job){

        logger.info("Executing " + JobValidator.class.getSimpleName() + " input: " + job);

        if (job.getJobTitle().isEmpty()){
            throw new JobNotValidException(ExceptionMessages.JOB_TITLE_REQUIRED.getMessage());
        }

        if (job.getCompanyName().isEmpty()){
            throw new JobNotValidException(ExceptionMessages.JOB_COMPANY_REQUIRED.getMessage());
        }

        if (job.getJobDescription().isEmpty()){
            throw new JobNotValidException(ExceptionMessages.JOB_DESCRIPTION_REQUIRED.getMessage());
        }

        if (job.getJobUrl().isEmpty()){
            throw new JobNotValidException(ExceptionMessages.JOB_URL_REQUIRED.getMessage());
        }

        if (job.getCustomUser() == null){
            throw new JobNotValidException(ExceptionMessages.USER_IS_REQUIRED_WITH_JOB.getMessage());
        }

    }
}
