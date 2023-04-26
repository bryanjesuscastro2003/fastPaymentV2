package com.cloud.cardsservice.application.port.out;

import com.cloud.cardsservice.application.port.in.commands.*;
import com.cloud.cardsservice.application.port.in.ports.CreateWalletPort;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;

public interface LoadWalletData {
    DataResponse createCard(CreateCardCommand createCardCommand);
    DataResponse createWallet(CreateWalletCommand createWalletCommand);
    DataResponse deleteCard(DeleteCardCommand deleteCardCommand);
    DataResponse getWallet(GetWalletCommand getWalletCommand);
    DataResponse updateCard(UpdateCardCommand updateCardCommand);
}
