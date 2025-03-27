package com.example.JobApplicationManager.model.entity;

import com.example.JobApplicationManager.service.jobsServices.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs_list")
public class JobsList {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_location")
    private String jobLocation;

    @Column(name = "job_level")
    private String jobLevel;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "job_salary")
    private String jobSalary;

    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "application_status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Column(name = "job_url")
    private String jobUrl;

    @Column(name = "favorite")
    private boolean favorite;

    @CreationTimestamp
    @Column(name = "date_added", nullable = false, updatable = false)
    private LocalDateTime localDateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "email", nullable = false)
    private CustomUser customUser;


}
