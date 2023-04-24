package com.cloud.authservice.adapters.in.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    private String name;
    private String lastname;
    private String email;
    private int phoneNumber;
    private String username;
    private String password;
}
