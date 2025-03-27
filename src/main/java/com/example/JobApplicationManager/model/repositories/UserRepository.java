package com.example.JobApplicationManager.model.repositories;

import com.example.JobApplicationManager.model.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, String> { //String is datatype of primary key

    @Query("SELECT query FROM CustomUser query WHERE query.emailVerificationToken = :emailVerificationToken")
    Optional<CustomUser> findByEmailVerificationToken(@Param("emailVerificationToken") String emailVerificationToken);



}
