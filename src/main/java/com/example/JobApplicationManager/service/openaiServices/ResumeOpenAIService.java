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
public class ResumeOpenAIService {

    @Value("${openai.api.key}")
    String apiKey;

    private final RestTemplate restTemplate;
    private final JobsRepository jobsRepository;
    private final ResumeScannerService resumeScannerService;
    private final OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ResumeOpenAIService.class);

    public ResumeOpenAIService(RestTemplate restTemplate,
                               JobsRepository jobsRepository,
                               ResumeScannerService resumeScannerService,
                               OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService,
                               UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.jobsRepository = jobsRepository;
        this.resumeScannerService = resumeScannerService;
        this.optionalCustomUserToCustomUserService = optionalCustomUserToCustomUserService;
        this.userRepository = userRepository;
    }

    public String analyzeResume(String jobId) throws IOException {
        logger.info("Executing " + getClass() + " || input: " + jobId);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        CustomUser user = optionalCustomUserToCustomUserService.execute(email);

        JobsList job = jobsRepository.findById(jobId)
                .orElseThrow(() -> new JobDoesNotExistException(ExceptionMessages.JOB_DOES_NOT_EXIST.getMessage()));

        if (user.getAuthority() == Authority.ADMIN) {
            return callToOpenAiApi(resumeScannerService.execute(), job.getJobDescription(), job.getJobTitle(), job.getCompanyName());
        }

        if (user.getNumOfAiRequests() >= 8) {
            return "Reached Max AI calls";
        }

        String response = callToOpenAiApi(resumeScannerService.execute(), job.getJobDescription(), job.getJobTitle(), job.getCompanyName());

        user.setNumOfAiRequests(user.getNumOfAiRequests() + 1);
        userRepository.save(user);

        return response + "\n\n You have " + (8 - user.getNumOfAiRequests()) + " AI requests left.";
    }


    private String callToOpenAiApi(String resume, String jobDescription, String jobTitle, String companyName) {
        logger.info("Executing " + getClass() + " Job Title: " + jobTitle + " || Company Name: " + companyName);

        String prompt = "Given these parameters: \n" +
                "Given Resume: " + resume + " ,Job Description: " + jobDescription + " ,Job title: " + jobTitle +
                " ,Company Name: " + companyName + "\n" +
                "Do these 3 steps: \n" +
                "Step 1: Focus on this step the least. Highlight the most relevant key bullet points from the resume for this role\n" +
                "Step 2: This is the step you should focus on the most.In order to maximize an interview offer, use the 3 parameters to edit bullet points in " +
                "the resume for this role to bypass Applicant Tracking Systems (ATS) by using key words. Return both the " +
                "edited bullet point along with the original to let the user easily figure out which bullet points need to be edited\n" +
                "Step 3: Give suggestions of what is missing in this resume based on the given parameters and what the user should focus on adding to their resume.\n" +
                "Limit the response to 325 words and do not make up information.";

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
