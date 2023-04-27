package com.cloud.cardsservice.adapters.in.net.auth_service.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptDataCommandResNet {
    private boolean ok;
    private String message;
    private Map<String, String> data;
}
