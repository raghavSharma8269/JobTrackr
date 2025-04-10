package com.example.JobApplicationManager.model.repositories;

import com.example.JobApplicationManager.model.entity.JobsList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<JobsList, String> { //String is datatype of primary key

    // finds job via custom user
    @Query("SELECT query FROM JobsList query WHERE query.customUser.email = :email")
    List<JobsList> findByCustomUser(@Param("email") String email);


    @Query("SELECT query FROM JobsList query WHERE query.customUser.email = :email " + // selects all jobs where email matches the email of the user sending request
            "AND (:keyword IS NULL OR :keyword = '' " + // if keyword is null or empty string, return all jobs
            "OR LOWER(query.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " + // if keyword is in job title, return job
            "OR LOWER(query.companyName) LIKE LOWER(CONCAT('%', :keyword, '%')) " + // if keyword is in company name, return job
            "OR LOWER(query.jobDescription) LIKE LOWER(CONCAT('%', :keyword, '%')))") // if keyword is in job description, return job
    List<JobsList> search(@Param("email") String email, @Param("keyword") String keyword, Sort sort); // search for jobs by email, keyword, and sort

}
