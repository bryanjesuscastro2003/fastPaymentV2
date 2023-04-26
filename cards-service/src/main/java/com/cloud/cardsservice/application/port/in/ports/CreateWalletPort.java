package com.cloud.cardsservice.application.port.in.ports;

import com.cloud.cardsservice.application.port.in.commands.CreateCardCommand;
import com.cloud.cardsservice.application.port.in.commands.CreateWalletCommand;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;

public interface CreateWalletPort {
    DataResponse createWallet(CreateWalletCommand createWalletCommand);
}
