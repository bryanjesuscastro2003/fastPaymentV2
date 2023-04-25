package com.cloud.authservice.application.port.in.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserCommand {
    private String name;
    private String lastname;
    private String email;
    private int phoneNumber;
    private String username;
    private String password;
}
