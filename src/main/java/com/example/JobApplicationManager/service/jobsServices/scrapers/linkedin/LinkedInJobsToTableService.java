package com.example.JobApplicationManager.service.jobsServices.scrapers.linkedin;

import com.example.JobApplicationManager.exceptions.validator.JobValidator;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.model.repositories.JobsRepository;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import com.example.JobApplicationManager.security.JwtUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LinkedInJobsToTableService {

    private final Logger logger = LoggerFactory.getLogger(LinkedInJobsToTableService.class);
    private final UserRepository userRepository;
    private final JobsRepository jobsRepository;

    public LinkedInJobsToTableService(UserRepository userRepository, JobsRepository jobsRepository) {
        this.userRepository = userRepository;
        this.jobsRepository = jobsRepository;
    }

    @Transactional
    public ResponseEntity<String> scrapeJobDetailsToTable(String url) throws Exception {
        logger.info("Executing " + getClass() + " input: " + url);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();


        // Fetch LinkedIn job page
        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();

        // Extract job details
        String jobTitle = getElementText(doc, "h1.top-card-layout__title");
        String jobDescription = getElementText(doc, "div.show-more-less-html__markup");
        String jobLocation = getElementText(doc, "span.topcard__flavor.topcard__flavor--bullet");
        String jobSalary = getElementText(doc, "div.compensation__salary");
        String companyName = getElementText(doc, "a.sub-nav-cta__optional-url");



        //Creating new job object to store in JobListEntity
        JobsList job = new JobsList();


        //validating email exists
        Optional<CustomUser> optionalCustomUser = userRepository.findById(email );
        if(optionalCustomUser.isPresent()){
            job.setCustomUser(optionalCustomUser.get()); //saving user to job listing
        }
        else {
            throw new Exception(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        // Create and save job
        job.setCompanyName(companyName);
        job.setJobTitle(jobTitle);
        job.setJobLocation(jobLocation);
        job.setJobSalary(jobSalary);
        job.setJobDescription(jobDescription);
        job.setApplicationStatus(null);
        job.setJobUrl(url);

        JobValidator.execute(job);

        jobsRepository.save(job);

        return ResponseEntity.ok().body("Job Successfully Added");
    }



    // Helper method to extract text
    private String getElementText(Document doc, String cssQuery) {
        Element element = doc.selectFirst(cssQuery);
        return (element != null) ? element.text() : "N/A";
    }

}
