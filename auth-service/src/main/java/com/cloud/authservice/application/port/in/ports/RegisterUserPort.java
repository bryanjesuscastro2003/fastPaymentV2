package com.cloud.authservice.application.port.in.ports;

import com.cloud.authservice.application.port.in.commands.RegisterUserCommand;
import com.cloud.authservice.application.port.out.commands.TokenCommand;
import com.cloud.authservice.domain.AuthDomain;

public interface RegisterUserPort {
   TokenCommand createProfile (RegisterUserCommand registerUserCommand);
}
