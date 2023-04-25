package com.cloud.authservice.adapters.out.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenDto {
    private boolean ok;
    private String message;
    private String token;
}
