package com.cloud.cardsservice.application.port.out.commands;

import com.cloud.cardsservice.adapters.out.model.CardModel;
import com.cloud.cardsservice.domain.CardDomain;
import com.cloud.cardsservice.domain.WalletDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DataResponse {
    private boolean ok;
    private String message;
    private WalletDomain wallet;
    private List<CardDomain> cards;
}
