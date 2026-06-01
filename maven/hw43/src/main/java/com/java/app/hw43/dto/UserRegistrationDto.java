package com.java.app.hw43.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String phone;

    @Size(min = 6)
    @NotBlank
    private String password;
}
