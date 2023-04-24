package com.cloud.authservice.adapters.out.services;

import com.cloud.authservice.adapters.in.dto.AuthUserDto;
import com.cloud.authservice.adapters.in.dto.RegisterUserDto;
import com.cloud.authservice.adapters.out.dto.TokenDto;
import com.cloud.authservice.adapters.out.model.AuthUserModel;
import com.cloud.authservice.adapters.out.repository.AuthUserRepository;
import com.cloud.authservice.adapters.out.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    public TokenDto loginProfile(AuthUserDto authUserDto){
        try {
            Optional<AuthUserModel> profile = authUserRepository.findByUsername(authUserDto.getUsername());
            if (profile.isEmpty())
                return TokenDto.builder()
                        .ok(false)
                        .message("Username not found")
                        .token(null)
                        .build();
            if (!passwordEncoder.matches(authUserDto.getPassword(), profile.get().getPassword()))
                return TokenDto.builder()
                        .ok(false)
                        .message("Password incorrect")
                        .token(null)
                        .build();
            var token = jwtProvider.generateJwt(profile.get());
            return TokenDto.builder()
                    .ok(true)
                    .message("Profile loaded ok")
                    .token(token)
                    .build();
        }catch (Exception e){
            return TokenDto.builder()
                    .ok(false)
                    .message("Unexpected error with the server :/")
                    .token(null)
                    .build();
        }
    }

    public TokenDto createProfile(RegisterUserDto registerUserDto){
        try{
            Optional<AuthUserModel> profile = authUserRepository.findByUsername(registerUserDto.getUsername());
            if (profile.isPresent())
                return TokenDto.builder()
                        .ok(false)
                        .message("Such username is already registered")
                        .token(null)
                        .build();
            String password = passwordEncoder.encode(registerUserDto.getPassword());
            AuthUserModel newProfile = AuthUserModel.builder()
                    .name(registerUserDto.getName())
                    .lastname(registerUserDto.getLastname())
                    .email(registerUserDto.getEmail())
                    .phoneNumber(registerUserDto.getPhoneNumber())
                    .password(password)
                    .role("USER")
                    .build();
            var token = jwtProvider.generateJwt(newProfile);
            return TokenDto.builder()
                    .ok(true)
                    .message("Profile loaded ok")
                    .token(token)
                    .build();
        }catch (Exception e){
            return TokenDto.builder()
                    .ok(false)
                    .message("Unexpected error with the server :/")
                    .token(null)
                    .build();
        }
    }

    public TokenDto validateToken(String token){
       try {
           if (!jwtProvider.isJwtValid(token))
               return TokenDto.builder()
                   .ok(false)
                   .message("Such username is already registered")
                   .token(null)
                   .build();
           String username = jwtProvider.extractUsername(token);
           if(authUserRepository.findByUsername(username).isEmpty())
               throw new Exception("Invalid token");
           return TokenDto.builder()
               .ok(true)
               .message("Health token")
               .token(token)
               .build();
       }catch (Exception e){
           return TokenDto.builder()
               .ok(false)
               .message("Unexpected error with the server :/")
               .token(null)
               .build();
       }
    }

}
