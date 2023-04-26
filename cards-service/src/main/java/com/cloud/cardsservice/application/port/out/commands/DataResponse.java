package com.cloud.cardsservice.application.port.out.commands;

import com.cloud.cardsservice.domain.WalletDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DataResponse {
    private boolean ok;
    private String message;
    private WalletDomain wallet;
}
