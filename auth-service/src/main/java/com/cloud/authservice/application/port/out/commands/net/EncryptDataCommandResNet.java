package com.cloud.authservice.application.port.out.commands.net;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EncryptDataCommandResNet {
    private boolean ok;
    private String message;
    private Map<String, String> data;
}
