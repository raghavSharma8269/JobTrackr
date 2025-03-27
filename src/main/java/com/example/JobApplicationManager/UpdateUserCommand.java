package com.example.JobApplicationManager;

import com.example.JobApplicationManager.model.entity.CustomUser;
import lombok.Getter;

@Getter
public class UpdateUserCommand {

    private String id;
    private CustomUser customUser;

    public UpdateUserCommand(String id, CustomUser customUser) {
        this.id = id;
        this.customUser = customUser;
    }
}