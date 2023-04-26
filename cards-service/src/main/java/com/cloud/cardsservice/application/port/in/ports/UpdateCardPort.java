package com.cloud.cardsservice.application.port.in.ports;

import com.cloud.cardsservice.application.port.in.commands.UpdateCardCommand;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;

public interface UpdateCardPort {
   DataResponse updateCard(UpdateCardCommand updateCardCommand);
}
