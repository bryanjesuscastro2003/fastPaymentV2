package com.cloud.authservice.application.service;

import com.cloud.authservice.application.config.UseCase;
import com.cloud.authservice.application.port.in.commands.net.EncryptDataCommand;
import com.cloud.authservice.application.port.in.commands.net.ValidateUserCommand;
import com.cloud.authservice.application.port.in.ports.net.EncryptDataPort;
import com.cloud.authservice.application.port.in.ports.net.ValidateUserPort;
import com.cloud.authservice.application.port.out.LoadNetData;
import com.cloud.authservice.application.port.out.commands.net.EncryptDataCommandResNet;
import com.cloud.authservice.application.port.out.commands.net.ValidateUserCommandResNet;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class NetDataService implements EncryptDataPort, ValidateUserPort {
    private final LoadNetData loadNetData;

    @Override
    public EncryptDataCommandResNet encryptData(EncryptDataCommand encryptDataCommand) {
        return loadNetData.encryptData(encryptDataCommand);
    }

    @Override
    public ValidateUserCommandResNet validateUser(ValidateUserCommand validateUserCommand) {
        return loadNetData.validateUser(validateUserCommand);
    }
}
