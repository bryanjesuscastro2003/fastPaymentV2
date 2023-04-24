package com.cloud.authservice.adapters.in.controller;

import com.cloud.authservice.adapters.in.dto.AuthUserDto;
import com.cloud.authservice.adapters.in.dto.RegisterUserDto;
import com.cloud.authservice.adapters.out.dto.TokenDto;
import com.cloud.authservice.adapters.out.services.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(
            @RequestBody AuthUserDto authUserDto
            ){
         TokenDto tokenDto = authUserService.loginProfile(authUserDto);
         return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/logup")
    public ResponseEntity<TokenDto> logUp(
            @RequestBody RegisterUserDto registerUserDto
            ){
         TokenDto tokenDto = authUserService.createProfile(registerUserDto);
         return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(
            @RequestParam String token
    ){
        TokenDto tokenDto = authUserService.validateToken(token);
        if(!tokenDto.isOk())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }
}
