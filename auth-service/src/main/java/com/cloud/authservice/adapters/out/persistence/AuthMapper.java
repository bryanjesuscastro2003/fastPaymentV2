package com.cloud.authservice.adapters.out.persistence;

import com.cloud.authservice.adapters.out.model.AuthUserModel;
import com.cloud.authservice.domain.AuthDomain;

public class AuthMapper {
    public static AuthDomain entityToDomain(AuthUserModel authUserModel){
         return AuthDomain.builder()
                 .id(authUserModel.getId())
                 .name(authUserModel.getName())
                 .lastname(authUserModel.getLastname())
                 .email(authUserModel.getEmail())
                 .phoneNumber(authUserModel.getPhoneNumber())
                 .username(authUserModel.getUsername())
                 .password(authUserModel.getPassword())
                 .build();
    }

    public static AuthUserModel domainToEntity(AuthDomain authDomain){
        return AuthUserModel.builder()
                .id(authDomain.getId())
                .name(authDomain.getName())
                .lastname(authDomain.getLastname())
                .email(authDomain.getEmail())
                .phoneNumber(authDomain.getPhoneNumber())
                .username(authDomain.getUsername())
                .password(authDomain.getPassword())
                .build();
    }

}
