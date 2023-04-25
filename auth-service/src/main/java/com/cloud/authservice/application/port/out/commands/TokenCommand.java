package com.cloud.authservice.application.port.out.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenCommand {
    private boolean ok;
    private String message;
    private String token;
    private String data;
}
