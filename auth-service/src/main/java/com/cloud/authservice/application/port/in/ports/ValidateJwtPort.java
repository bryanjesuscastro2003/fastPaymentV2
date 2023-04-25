package com.cloud.authservice.application.port.in.ports;

import com.cloud.authservice.application.port.out.commands.TokenCommand;

public interface ValidateJwtPort {
    TokenCommand validateToken(String token);
}
