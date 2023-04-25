package com.cloud.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDomain {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private int phoneNumber;
    private String username;
    private String password;
    private String role;
}
