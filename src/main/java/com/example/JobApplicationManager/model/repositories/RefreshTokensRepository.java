package com.example.JobApplicationManager.model.repositories;

import com.example.JobApplicationManager.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokensRepository extends JpaRepository<RefreshToken, String> { //String is datatype of primary key
}
