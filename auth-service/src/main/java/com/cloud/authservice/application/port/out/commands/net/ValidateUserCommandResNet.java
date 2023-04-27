package com.cloud.authservice.application.port.out.commands.net;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ValidateUserCommandResNet {
    private boolean ok;
    private String message;
}
