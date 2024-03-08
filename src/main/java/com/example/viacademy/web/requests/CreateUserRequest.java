package com.example.viacademy.web.requests;

import jakarta.persistence.Column;
import lombok.Getter;

import java.util.Date;

@Getter
public class CreateUserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
