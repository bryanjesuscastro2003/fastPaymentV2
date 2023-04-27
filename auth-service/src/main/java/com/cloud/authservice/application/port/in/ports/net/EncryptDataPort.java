package com.cloud.authservice.application.port.in.ports.net;

import com.cloud.authservice.application.port.in.commands.net.EncryptDataCommand;
import com.cloud.authservice.application.port.out.commands.net.EncryptDataCommandResNet;

public interface EncryptDataPort {
    EncryptDataCommandResNet encryptData(EncryptDataCommand encryptDataCommand);
}
