package com.example.JobApplicationManager.service.adminServices;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.UnauthorizedException;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.model.repositories.JobsRepository;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import com.example.JobApplicationManager.service.OptionalCustomUserToCustomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteUserViaAdminService {

    private final UserRepository userRepository;
    private final JobsRepository jobsRepository;
    private final OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService;
    private final Logger logger = LoggerFactory.getLogger(DeleteUserViaAdminService.class);

    public DeleteUserViaAdminService(UserRepository userRepository,
                                     JobsRepository jobsRepository,
                                     OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService) {
        this.userRepository = userRepository;
        this.jobsRepository = jobsRepository;
        this.optionalCustomUserToCustomUserService = optionalCustomUserToCustomUserService;
    }

    public ResponseEntity<String> execute (String email){

        CustomUser user = optionalCustomUserToCustomUserService.execute(email);

        logger.info("Deleting user: " + email);

        if (user.getEmail().equals(email)) {

            List<JobsList> jobsLists = jobsRepository.findByCustomUser(email);

            if (!jobsLists.isEmpty()) {
                jobsRepository.deleteAll(jobsLists);
            }
            userRepository.delete(user);
            return ResponseEntity.ok().body("User Successfully Deleted");
        }

        else {
            throw new UnauthorizedException(ExceptionMessages.NOT_AUTHORIZED_TO_DELETE_USER.getMessage());
        }


    }

}
