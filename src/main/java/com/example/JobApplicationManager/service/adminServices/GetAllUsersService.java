package com.example.JobApplicationManager.service.adminServices;

import com.example.JobApplicationManager.model.DTOs.UserDTO;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersService {

    final private UserRepository userRepository;
    final private Logger logger = LoggerFactory.getLogger(GetAllUsersService.class);

    public GetAllUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<UserDTO>> execute() {

        logger.info("Executing " + getClass());

        List<CustomUser> allUsers = userRepository.findAll();

        List<UserDTO> allUsersDTO = allUsers
                .stream()
                .map(UserDTO ::new)
                .toList();

        return ResponseEntity.ok().body(allUsersDTO);

    }

}
