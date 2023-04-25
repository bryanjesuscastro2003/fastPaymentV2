package com.cloud.authservice.application.port.out;

import com.cloud.authservice.application.port.in.commands.AuthUserCommand;
import com.cloud.authservice.application.port.in.commands.RegisterUserCommand;
import com.cloud.authservice.application.port.out.commands.TokenCommand;
import com.cloud.authservice.domain.AuthDomain;
public interface LoadAuthUserData {
    TokenCommand loginProfile(AuthUserCommand authUserCommand);
    TokenCommand createProfile (RegisterUserCommand registerUserCommand);
    TokenCommand validateToken (String token);
}
