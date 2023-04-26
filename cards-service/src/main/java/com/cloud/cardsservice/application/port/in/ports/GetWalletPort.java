package com.cloud.cardsservice.application.port.in.ports;

import com.cloud.cardsservice.application.port.in.commands.GetWalletCommand;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;

public interface GetWalletPort {
    DataResponse getWallet(GetWalletCommand getWalletCommand);
}
