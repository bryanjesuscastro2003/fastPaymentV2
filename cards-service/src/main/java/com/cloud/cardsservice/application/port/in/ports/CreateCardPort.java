package com.cloud.cardsservice.application.port.in.ports;

import com.cloud.cardsservice.application.port.in.commands.CreateCardCommand;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;

public interface CreateCardPort {
    DataResponse createCard(CreateCardCommand createCardCommand);
}
