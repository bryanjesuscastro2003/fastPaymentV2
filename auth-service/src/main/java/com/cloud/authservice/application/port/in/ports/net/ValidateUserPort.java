package com.cloud.authservice.application.port.in.ports.net;

import com.cloud.authservice.application.port.in.commands.net.ValidateUserCommand;
import com.cloud.authservice.application.port.out.commands.net.ValidateUserCommandResNet;

public interface ValidateUserPort {
    ValidateUserCommandResNet validateUser(ValidateUserCommand validateUserCommand);
}
