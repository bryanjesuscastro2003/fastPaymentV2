package com.cloud.authservice.application.port.out;

import com.cloud.authservice.application.port.in.commands.net.EncryptDataCommand;
import com.cloud.authservice.application.port.in.commands.net.ValidateUserCommand;
import com.cloud.authservice.application.port.out.commands.net.EncryptDataCommandResNet;
import com.cloud.authservice.application.port.out.commands.net.ValidateUserCommandResNet;

public interface LoadNetData {
    EncryptDataCommandResNet encryptData(EncryptDataCommand encryptDataCommand);
    ValidateUserCommandResNet validateUser(ValidateUserCommand validateUserCommand);
}
