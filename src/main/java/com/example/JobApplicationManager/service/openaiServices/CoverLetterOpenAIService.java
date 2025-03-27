package com.example.JobApplicationManager.service.openaiServices;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.ExternalApiServiceDownException;
import com.example.JobApplicationManager.exceptions.JobDoesNotExistException;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.model.entity.JobsList;
import com.example.JobApplicationManager.model.repositories.JobsRepository;
import com.example.JobApplicationManager.model.repositories.UserRepository;
import com.example.JobApplicationManager.security.Authority;
import com.example.JobApplicationManager.service.OptionalCustomUserToCustomUserService;
import com.example.JobApplicationManager.service.resumeServices.CoverLetterScannerService;
import com.example.JobApplicationManager.service.resumeServices.ResumeScannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoverLetterOpenAIService {

    @Value("${openai.api.key}")
    String apiKey;

    private final RestTemplate restTemplate;
    private final JobsRepository jobsRepository;
    private final ResumeScannerService resumeScannerService;
    private final CoverLetterScannerService coverLetterScannerService;
    private final OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(CoverLetterOpenAIService.class);

    public CoverLetterOpenAIService(RestTemplate restTemplate,
                                    JobsRepository jobsRepository,
                                    ResumeScannerService resumeScannerService,
                                    CoverLetterScannerService coverLetterScannerService,
                                    OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService,
                                    UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.jobsRepository = jobsRepository;
        this.resumeScannerService = resumeScannerService;
        this.coverLetterScannerService = coverLetterScannerService;
        this.optionalCustomUserToCustomUserService = optionalCustomUserToCustomUserService;
        this.userRepository = userRepository;
    }

    public String analyzeCoverLetter(String jobId) throws IOException {
        logger.info("Executing " + getClass() + " || input: " + jobId);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        CustomUser user = optionalCustomUserToCustomUserService.execute(email);

        JobsList job = jobsRepository.findById(jobId)
                .orElseThrow(() -> new JobDoesNotExistException(ExceptionMessages.JOB_DOES_NOT_EXIST.getMessage()));

        if (user.getAuthority() == Authority.ADMIN) {
            return callToOpenAiApi(resumeScannerService.execute(), job.getJobDescription(), job.getJobTitle(), job.getCompanyName(), coverLetterScannerService.execute());
        }

        if (user.getNumOfAiRequests() >= 8) {
            return "You have no more AI requests left.";
        }

        String updatedCoverLetter = callToOpenAiApi(resumeScannerService.execute(), job.getJobDescription(), job.getJobTitle(), job.getCompanyName(), coverLetterScannerService.execute());

        user.setNumOfAiRequests(user.getNumOfAiRequests() + 1);
        userRepository.save(user);

        return updatedCoverLetter + "\n\n You have " + (8 - user.getNumOfAiRequests()) + " AI requests left.";
    }


    private String callToOpenAiApi(String resume, String jobDescription, String jobTitle, String companyName, String coverLetter) {
        logger.info("Executing " + getClass() + " Job Title: " + jobTitle + " || Company Name: " + companyName);

        String prompt = "Given these parameters: \n" +
                "Given Resume: " + resume + " ,Job Description: " + jobDescription + " ,Job title: " + jobTitle +
                " ,Company Name: " + companyName + " ,Cover Letter: " + coverLetter + "\n" +
                "Do these 3 steps: \n" +
                "You are a career coach with over 15 years of experience helping job seekers land their dream jobs in " +
                "tech. You are helping a candidate to write a cover letter for the below role. Approach this task in " +
                "three steps. Step 1. Identify main challenges someone in this position would face day to day. " +
                "Step 2. Write an attention grabbing hook for your cover letter that highlights your experience and " +
                "qualifications in a way that shows you empathize and can successfully take on challenges of the role. " +
                "Consider incorporating specific examples of how you tackled these challenges in your past work, and " +
                "explore creative ways to express your enthusiasm for the opportunity. Put emphasis on how the candidate " +
                "can contribute to company as opposed to just listing accomplishments. Keep your hook within 100 words " +
                "or less. Step 3. Finish writing the cover letter based on the resume and keep it within 250 words. " +
                "Respond with final cover letter only and do not make up information.";

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are an assistant that helps users improve resumes for job applications.");
        messages.add(systemMessage);

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.add(userMessage);

        // Prepare request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.5);

        // Set up HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // Send the request
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        String openAiUrl = "https://api.openai.com/v1/chat/completions";
        ResponseEntity<Map> response = restTemplate.exchange(openAiUrl, HttpMethod.POST, request, Map.class);

        // Extract the improved resume text from the OpenAI response
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("choices")) {
            // Extract the generated message content from the response
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            if (!choices.isEmpty()) {
                Map<String, Object> choice = choices.get(0);
                Map<String, String> message = (Map<String, String>) choice.get("message");
                return message.get("content");  // Return the response content
            }
        }

        throw new ExternalApiServiceDownException(ExceptionMessages.OPEN_AI_API_IS_DOWN.getMessage());
    }


}
