package com.cloud.authservice.application.port.in.ports;

import com.cloud.authservice.application.port.in.commands.AuthUserCommand;
import com.cloud.authservice.application.port.in.commands.RegisterUserCommand;
import com.cloud.authservice.application.port.out.commands.TokenCommand;
import com.cloud.authservice.domain.AuthDomain;

public interface AuthUserPort {
   TokenCommand loginProfile(AuthUserCommand authUserCommand);
}
