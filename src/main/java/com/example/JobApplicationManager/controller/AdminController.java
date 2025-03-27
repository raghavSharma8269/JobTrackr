package com.example.JobApplicationManager.controller;

import com.example.JobApplicationManager.model.DTOs.UserDTO;
import com.example.JobApplicationManager.service.adminServices.DeleteUserViaAdminService;
import com.example.JobApplicationManager.service.adminServices.GetAllUsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final DeleteUserViaAdminService deleteUserViaAdminService;
    private final GetAllUsersService getAllUsersService;

    public AdminController(DeleteUserViaAdminService deleteUserViaAdminService,
                           GetAllUsersService getAllUsersService
    ) {
        this.deleteUserViaAdminService = deleteUserViaAdminService;
        this.getAllUsersService = getAllUsersService;
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser (@PathVariable String email) {
        return deleteUserViaAdminService.execute(email);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return getAllUsersService.execute();
    }

}
