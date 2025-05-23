package com.example.JobApplicationManager.controller;

import com.example.JobApplicationManager.model.DTOs.ResetPasswordDTO;
import com.example.JobApplicationManager.service.openaiServices.CoverLetterOpenAIService;
import com.example.JobApplicationManager.service.openaiServices.ResumeOpenAIService;
import com.example.JobApplicationManager.service.resumeServices.CoverLetterScannerService;
import com.example.JobApplicationManager.service.resumeServices.ResumeScannerService;
import com.example.JobApplicationManager.service.resumeServices.UploadCoverLetterService;
import com.example.JobApplicationManager.service.resumeServices.UploadResumeService;
import com.example.JobApplicationManager.service.userServices.DeleteUserService;
import com.example.JobApplicationManager.service.userServices.ResetPasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UploadResumeService uploadResumeService;
    private final UploadCoverLetterService uploadCoverLetterService;
    private final ResumeOpenAIService resumeOpenAIService;
    private final CoverLetterOpenAIService coverLetterOpenAIService;
    private final ResumeScannerService resumeScannerService;
    private final CoverLetterScannerService coverLetterScannerService;
    private final DeleteUserService deleteUserService;
    private final ResetPasswordService resetPasswordService;

    public ProfileController(UploadResumeService uploadResumeService,
                             UploadCoverLetterService uploadCoverLetterService,
                             ResumeOpenAIService resumeOpenAIService,
                             CoverLetterOpenAIService coverLetterOpenAIService,
                             ResumeScannerService resumeScannerService,
                             CoverLetterScannerService coverLetterScannerService,
                             DeleteUserService deleteUserService,
                             ResetPasswordService resetPasswordService
    ) {
        this.uploadResumeService = uploadResumeService;
        this.uploadCoverLetterService = uploadCoverLetterService;
        this.resumeOpenAIService = resumeOpenAIService;
        this.coverLetterOpenAIService = coverLetterOpenAIService;
        this.resumeScannerService = resumeScannerService;
        this.coverLetterScannerService = coverLetterScannerService;
        this.deleteUserService = deleteUserService;
        this.resetPasswordService = resetPasswordService;
    }

    /**
     * RESUME REQUESTS
     */

    // upload resume
    @PostMapping("/resume")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> uploadResume (@RequestParam MultipartFile resumeFile) throws IOException {
        return uploadResumeService.execute(resumeFile);
    }

    // get ai feedback
    @GetMapping("/resume/{jobID}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String analyzeResume (@PathVariable String jobID) throws IOException {
        return resumeOpenAIService.analyzeResume(jobID);
    }

    // TESTING RESUME SCANNER ENDPOINT
    @GetMapping("resume/scan")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String scanResume () throws IOException {
        return resumeScannerService.execute();
    }

    /**
     * COVER LETTER REQUESTS
     */

    // upload cover letter
    @PostMapping("/cv")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> uploadCoverLetter (@RequestParam MultipartFile coverLetterFile) throws IOException {
        return uploadCoverLetterService.execute(coverLetterFile);
    }

    // get ai feedback
    @GetMapping("cv")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String analyzeCoverLetter (@RequestParam String jobID) throws IOException {
        return coverLetterOpenAIService.analyzeCoverLetter(jobID);
    }

    // TESTING CV SCANNER ENDPOINT
    @GetMapping("cv/scan")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String scanCoverLetter () throws IOException {
        return coverLetterScannerService.execute();
    }

    @DeleteMapping()
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser () {
        return deleteUserService.execute();
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> resetUserPassword (@RequestBody ResetPasswordDTO request) {
        return resetPasswordService.execute(request);
    }

}
