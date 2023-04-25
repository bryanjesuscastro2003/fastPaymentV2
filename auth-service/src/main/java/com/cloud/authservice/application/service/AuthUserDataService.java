package com.cloud.authservice.application.service;


import com.cloud.authservice.application.config.UseCase;
import com.cloud.authservice.application.port.in.commands.AuthUserCommand;
import com.cloud.authservice.application.port.in.commands.RegisterUserCommand;
import com.cloud.authservice.application.port.in.ports.AuthUserPort;
import com.cloud.authservice.application.port.in.ports.RegisterUserPort;
import com.cloud.authservice.application.port.in.ports.ValidateJwtPort;
import com.cloud.authservice.application.port.out.LoadAuthUserData;
import com.cloud.authservice.application.port.out.commands.TokenCommand;
import com.cloud.authservice.domain.AuthDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class AuthUserDataService implements AuthUserPort, RegisterUserPort, ValidateJwtPort {

    private final LoadAuthUserData loadAuthUserData;
    public AuthUserDataService(LoadAuthUserData loadAuthUserData) {
        this.loadAuthUserData = loadAuthUserData;
    }

    @Transactional
    @Override
    public TokenCommand loginProfile(AuthUserCommand authUserCommand) {
        return loadAuthUserData.loginProfile(authUserCommand);
    }

    @Transactional
    @Override
    public TokenCommand createProfile(RegisterUserCommand registerUserCommand) {
        return loadAuthUserData.createProfile(registerUserCommand);
    }

    @Transactional
    @Override
    public TokenCommand validateToken(String token) {
        return loadAuthUserData.validateToken(token);
    }
}
