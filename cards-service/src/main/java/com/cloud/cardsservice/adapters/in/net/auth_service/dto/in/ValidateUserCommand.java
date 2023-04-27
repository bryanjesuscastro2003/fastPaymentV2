package com.cloud.cardsservice.adapters.in.net.auth_service.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateUserCommand {
    private String user;
}
