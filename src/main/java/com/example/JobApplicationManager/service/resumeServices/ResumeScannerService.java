package com.example.JobApplicationManager.service.resumeServices;

import com.example.JobApplicationManager.exceptions.ExceptionMessages;
import com.example.JobApplicationManager.exceptions.ResumeMissingException;
import com.example.JobApplicationManager.model.entity.CustomUser;
import com.example.JobApplicationManager.service.OptionalCustomUserToCustomUserService;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResumeScannerService {

    private final OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService;
    private final PDFTextStripper pdfTextStripper;
    Logger logger = LoggerFactory.getLogger(ResumeScannerService.class);

    public ResumeScannerService(OptionalCustomUserToCustomUserService optionalCustomUserToCustomUserService, PDFTextStripper pdfTextStripper) {
        this.optionalCustomUserToCustomUserService = optionalCustomUserToCustomUserService;
        this.pdfTextStripper = pdfTextStripper;
    }

    public String execute() throws IOException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        logger.info("Executing " + getClass() + " using email: " + email);


        CustomUser user = optionalCustomUserToCustomUserService.execute(email);

        if (user.getResume() != null) {
            PDDocument document = Loader.loadPDF(user.getResume());
            return pdfTextStripper.getText(document);
        }

      else {
          throw new ResumeMissingException(ExceptionMessages.RESUME_MISSING.getMessage());
        }

    }

}
