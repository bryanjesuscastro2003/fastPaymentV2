package com.cloud.cardsservice.application.service;

import com.cloud.cardsservice.application.port.config.UseCase;
import com.cloud.cardsservice.application.port.in.commands.*;
import com.cloud.cardsservice.application.port.in.ports.*;
import com.cloud.cardsservice.application.port.out.LoadWalletData;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class WalletDataService implements CreateCardPort, CreateWalletPort, DeleteCardPort, GetWalletPort, UpdateCardPort {
    private final LoadWalletData loadWalletData;
    @Override
    public DataResponse createCard(CreateCardCommand createCardCommand) {
        return loadWalletData.createCard(createCardCommand);
    }

    @Override
    public DataResponse createWallet(CreateWalletCommand createWalletPort) {
        return loadWalletData.createWallet(createWalletPort);
    }

    @Override
    public DataResponse deleteCard(DeleteCardCommand deleteCardCommand) {
        return loadWalletData.deleteCard(deleteCardCommand);
    }

    @Override
    public DataResponse getWallet(GetWalletCommand getWalletCommand) {
        return loadWalletData.getWallet(getWalletCommand);
    }

    @Override
    public DataResponse updateCard(UpdateCardCommand updateCardCommand) {
        return loadWalletData.updateCard(updateCardCommand);
    }
}
