package com.cloud.cardsservice.adapters.in.controllers;

import com.cloud.cardsservice.adapters.out.model.CardModel;
import com.cloud.cardsservice.adapters.out.model.WalletModel;
import com.cloud.cardsservice.adapters.out.repository.CardRepository;
import com.cloud.cardsservice.adapters.out.repository.WalletRepository;
import com.cloud.cardsservice.application.port.config.WebAdapter;
import com.cloud.cardsservice.application.port.in.commands.CreateCardCommand;
import com.cloud.cardsservice.application.port.in.commands.CreateWalletCommand;
import com.cloud.cardsservice.application.port.in.commands.GetWalletCommand;
import com.cloud.cardsservice.application.port.in.ports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/cards")
public class CardsController {

    private final CreateCardPort createCardPort;
    private final CreateWalletPort createWalletPort;
    private final DeleteCardPort deleteCardPort;
    private final GetWalletPort getWalletPort;
    private final UpdateCardPort updateCardPort;

    public CardsController(CreateCardPort createCardPort, CreateWalletPort createWalletPort, DeleteCardPort deleteCardPort, GetWalletPort getWalletPort, UpdateCardPort updateCardPort) {
        this.createCardPort = createCardPort;
        this.createWalletPort = createWalletPort;
        this.deleteCardPort = deleteCardPort;
        this.getWalletPort = getWalletPort;
        this.updateCardPort = updateCardPort;
    }

    @GetMapping("/createCard")
    public String createCard(){
        createCardPort.createCard(CreateCardCommand.builder()
                        .user("cavernicola")
                        .description("Test card cavernicola me")
                .build());
        return "card created";
    }

    @GetMapping("/createWallet")
    public String myCards(){
        createWalletPort.createWallet(CreateWalletCommand.builder()
                        .user("cavernicola")
                .build());
        return "Walled created";
    }

    @GetMapping("/test")
    public String test(){
        getWalletPort.getWallet(GetWalletCommand.builder()
                        .tuitionCard("")
                .build());
        return " test";
    }
}
