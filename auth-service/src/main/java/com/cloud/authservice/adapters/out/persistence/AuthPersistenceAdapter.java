package com.cloud.authservice.adapters.out.persistence;

import com.cloud.authservice.adapters.out.model.AuthUserModel;
import com.cloud.authservice.adapters.out.repository.AuthUserRepository;
import com.cloud.authservice.adapters.out.security.JwtProvider;
import com.cloud.authservice.application.config.PersistenceAdapter;
import com.cloud.authservice.application.port.in.commands.AuthUserCommand;
import com.cloud.authservice.application.port.in.commands.RegisterUserCommand;
import com.cloud.authservice.application.port.out.LoadAuthUserData;
import com.cloud.authservice.application.port.out.commands.TokenCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@PersistenceAdapter
public class AuthPersistenceAdapter implements LoadAuthUserData {

    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public TokenCommand loginProfile(AuthUserCommand authUserCommand) {
        try {
            Optional<AuthUserModel> profile = authUserRepository.findByUsername(authUserCommand.getUsername());
            if (profile.isEmpty())
                return TokenCommand.builder()
                        .ok(false)
                        .message("Username not found")
                        .token(null)
                        .build();
            if (!passwordEncoder.matches(authUserCommand.getPassword(), profile.get().getPassword()))
                return TokenCommand.builder()
                        .ok(false)
                        .message("Password incorrect")
                        .token(null)
                        .build();
            var token = jwtProvider.generateJwt(profile.get());
            return TokenCommand.builder()
                    .ok(true)
                    .message("Profile loaded ok")
                    .token(token)
                    .build();
        }catch (Exception e){
            return TokenCommand.builder()
                    .ok(false)
                    .message("Unexpected error with the server :/")
                    .token(null)
                    .build();
        }
    }

    @Override
    public TokenCommand createProfile(RegisterUserCommand registerUserCommand) {
        try{
            Optional<AuthUserModel> profile = authUserRepository.findByUsername(registerUserCommand.getUsername());
            if (profile.isPresent())
                return TokenCommand.builder()
                        .ok(false)
                        .message("Such username is already registered")
                        .token(null)
                        .build();
            String password = passwordEncoder.encode(registerUserCommand.getPassword());
            AuthUserModel newProfile = AuthUserModel.builder()
                    .name(registerUserCommand.getName())
                    .lastname(registerUserCommand.getLastname())
                    .email(registerUserCommand.getEmail())
                    .phoneNumber(registerUserCommand.getPhoneNumber())
                    .username(registerUserCommand.getUsername())
                    .password(password)
                    .role("USER")
                    .build();
            authUserRepository.insert(newProfile);
            var token = jwtProvider.generateJwt(newProfile);
            return TokenCommand.builder()
                    .ok(true)
                    .message("Profile loaded ok")
                    .token(token)
                    .build();
        }catch (Exception e){
            return TokenCommand.builder()
                    .ok(false)
                    .message("Unexpected error with the server :/")
                    .token(null)
                    .build();
        }
    }

    @Override
    public TokenCommand validateToken(String token) {
        try {
            if (!jwtProvider.isJwtValid(token))
                return TokenCommand.builder()
                        .ok(false)
                        .message("Such username is already registered")
                        .token(null)
                        .build();
            String username = jwtProvider.extractUsername(token);
            if(authUserRepository.findByUsername(username).isEmpty())
                throw new Exception("Invalid token");
            return TokenCommand.builder()
                    .ok(true)
                    .message("Health token")
                    .token(token)
                    .data(username)
                    .build();
        }catch (Exception e){
            return TokenCommand.builder()
                    .ok(false)
                    .message("Unexpected error with the server :/")
                    .token(null)
                    .build();
        }
    }
}
