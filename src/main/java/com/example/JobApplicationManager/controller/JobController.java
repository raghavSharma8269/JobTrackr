package com.example.JobApplicationManager.controller;

import com.example.JobApplicationManager.UpdateJobCommand;
import com.example.JobApplicationManager.service.jobsServices.*;
import com.example.JobApplicationManager.model.DTOs.JobsDTO;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.service.jobsServices.scrapers.linkedin.LinkedInJobsToTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final LinkedInJobsToTableService linkedInJobsToTableService;
    private final CreateNewCustomJobService createNewCustomJobService;
    private final GetAllJobsService getAllJobsService;
    private final UpdateJobService updateJobService;
    private final DeleteJobService deleteJobService;

    public JobController(LinkedInJobsToTableService linkedInJobsToTableService,
                         CreateNewCustomJobService createNewCustomJobService,
                         GetAllJobsService getAllJobsService,
                         UpdateJobService updateJobService,
                         DeleteJobService deleteJobService
    ) {
        this.linkedInJobsToTableService = linkedInJobsToTableService;
        this.createNewCustomJobService = createNewCustomJobService;
        this.getAllJobsService = getAllJobsService;
        this.updateJobService = updateJobService;
        this.deleteJobService = deleteJobService;
    }

    // linkedin url -> table
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> linkedinToTable (@RequestParam String url) throws Exception {
        return linkedInJobsToTableService.scrapeJobDetailsToTable(url);
    }

    // custom job -> table
    @PostMapping("/create/custom")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> jobToTable(@RequestBody JobsList job) {
        return createNewCustomJobService.execute(job);
    }

    // GET all jobs
    @GetMapping()
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<JobsDTO>> getAllJobs(@RequestParam(defaultValue = "localDateTime") String sortBy,
                                                    @RequestParam(required = false) String search,
                                                    @RequestParam(required = false) ApplicationStatus status) {
        return getAllJobsService.execute(sortBy, search, status );
    }

    // Update existing job
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<JobsDTO> updateJob (@PathVariable String id, @RequestBody JobsList job){
        return updateJobService.execute(new UpdateJobCommand(id, job));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteJob(@PathVariable String id) {
        return deleteJobService.execute(id);
    }

}
