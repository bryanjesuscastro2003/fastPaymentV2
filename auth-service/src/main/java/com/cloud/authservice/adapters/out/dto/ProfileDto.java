package com.cloud.authservice.adapters.out.dto;

import com.cloud.authservice.adapters.out.model.AuthUserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private boolean ok;
    private String message;
    private AuthUserModel data;
}
