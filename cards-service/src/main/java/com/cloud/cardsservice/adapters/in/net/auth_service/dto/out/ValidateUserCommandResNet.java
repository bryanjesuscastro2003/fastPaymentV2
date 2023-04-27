package com.cloud.cardsservice.adapters.in.net.auth_service.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateUserCommandResNet {
    private boolean ok;
    private String message;
}
