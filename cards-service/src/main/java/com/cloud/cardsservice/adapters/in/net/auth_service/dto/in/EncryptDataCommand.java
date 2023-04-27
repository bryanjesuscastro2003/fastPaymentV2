package com.cloud.cardsservice.adapters.in.net.auth_service.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptDataCommand {
    private Map<String, String> data;
}
