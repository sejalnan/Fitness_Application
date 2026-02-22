package com.project.fitness.dto;

import com.project.fitness.enums.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {

    private  String email;

    private String password;

    private String firstName;

    private String lastName;

    private UserRole role;
}
