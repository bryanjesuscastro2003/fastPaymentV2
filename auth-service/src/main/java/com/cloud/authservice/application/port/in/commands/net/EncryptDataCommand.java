package com.cloud.authservice.application.port.in.commands.net;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncryptDataCommand {
    private Map<String, String> data;
}
