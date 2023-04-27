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
import com.cloud.cardsservice.application.port.out.commands.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/money")
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

    @PostMapping("/createWallet")
    public ResponseEntity<DataResponse> myCards(
            @RequestBody CreateWalletCommand request
    ){
        System.out.println(request + " request");
        return ResponseEntity.ok(createWalletPort.createWallet(request));
    }

    @PostMapping("/createCard")
    public ResponseEntity<DataResponse> createCard(
            @RequestBody CreateCardCommand request
    ){
        return ResponseEntity.ok(createCardPort.createCard(request));
    }

    @GetMapping("/myWallet")
    public ResponseEntity<DataResponse> getUserWallet(
            @RequestParam String user
    ){
        return ResponseEntity.ok(getWalletPort.getWallet(GetWalletCommand.builder()
                        .user(user)
                .build()));
    }

    @GetMapping("/test")
    public String test(){

        return " test";
    }
}
