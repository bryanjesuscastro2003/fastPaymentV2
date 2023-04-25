package com.cloud.authservice.adapters.in.controller;

import com.cloud.authservice.application.config.WebAdapter;
import com.cloud.authservice.application.port.in.commands.AuthUserCommand;
import com.cloud.authservice.application.port.in.commands.RegisterUserCommand;
import com.cloud.authservice.application.port.in.ports.AuthUserPort;
import com.cloud.authservice.application.port.in.ports.RegisterUserPort;
import com.cloud.authservice.application.port.in.ports.ValidateJwtPort;
import com.cloud.authservice.application.port.out.commands.TokenCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserPort authUserPort;
    private final RegisterUserPort registerUserPort;
    private final ValidateJwtPort validateJwtPort;

    public AuthController(AuthUserPort authUserPort, RegisterUserPort registerUserPort, ValidateJwtPort validateJwtPort) {
        this.authUserPort = authUserPort;
        this.registerUserPort = registerUserPort;
        this.validateJwtPort = validateJwtPort;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenCommand> login(
            @RequestBody AuthUserCommand authUserDto
    ){
        return ResponseEntity.ok(authUserPort.loginProfile(authUserDto));
    }

    @PostMapping("/logup")
    public ResponseEntity<TokenCommand> logUp(
            @RequestBody RegisterUserCommand registerUserDto
    ){
        return ResponseEntity.ok(registerUserPort.createProfile(registerUserDto));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenCommand> validate(
            @RequestParam String token
    ){
        TokenCommand tokenDto = validateJwtPort.validateToken(token);
        if(!tokenDto.isOk())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

}
