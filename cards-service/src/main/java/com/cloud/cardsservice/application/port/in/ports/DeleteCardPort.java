package com.cloud.cardsservice.application.port.in.ports;

import com.cloud.cardsservice.application.port.in.commands.DeleteCardCommand;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;

public interface DeleteCardPort {
    DataResponse deleteCard(DeleteCardCommand deleteCardCommand);
}
