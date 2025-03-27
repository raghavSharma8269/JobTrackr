package com.example.JobApplicationManager;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface Command <I,O>{
    ResponseEntity<O> execute (I input) throws MessagingException;
}
