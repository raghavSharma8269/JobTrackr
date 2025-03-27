package com.example.JobApplicationManager.configs;

import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

@Configuration
public class PdfConfig {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

    @Bean
    public PDFTextStripper pdfTextStripper() throws IOException {
        logger.info("Initializing PDFTextStripper...");
        return new PDFTextStripper();
    }
}
