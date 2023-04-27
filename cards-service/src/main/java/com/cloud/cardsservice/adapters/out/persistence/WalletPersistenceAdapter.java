package com.cloud.cardsservice.adapters.out.persistence;

import com.cloud.cardsservice.adapters.in.net.auth_service.dto.in.EncryptDataCommand;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.in.ValidateUserCommand;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.out.EncryptDataCommandResNet;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.out.ValidateUserCommandResNet;
import com.cloud.cardsservice.adapters.in.net.auth_service.service.AuthServiceFeign;
import com.cloud.cardsservice.adapters.in.utils.CardActions;
import com.cloud.cardsservice.adapters.out.ModelMapper;
import com.cloud.cardsservice.adapters.out.model.CardModel;
import com.cloud.cardsservice.adapters.out.model.WalletModel;
import com.cloud.cardsservice.adapters.out.services.mongo.MongoServiceCard;
import com.cloud.cardsservice.adapters.out.services.mongo.MongoServiceWallet;
import com.cloud.cardsservice.application.port.config.PersistenceAdapter;
import com.cloud.cardsservice.application.port.in.commands.*;
import com.cloud.cardsservice.application.port.out.LoadWalletData;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;
import com.cloud.cardsservice.domain.CardDomain;
import com.cloud.cardsservice.domain.WalletDomain;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@PersistenceAdapter
public class WalletPersistenceAdapter implements LoadWalletData {

    @Autowired
    private AuthServiceFeign authServiceFeign;
    @Autowired
    private MongoServiceWallet mongoServiceWallet;
    @Autowired
    private MongoServiceCard mongoServiceCard;

    @Override
    public DataResponse createWallet(CreateWalletCommand createWalletCommand) {
        try {
            ValidateUserCommandResNet resNet = authServiceFeign.validateUser(
                    ValidateUserCommand.builder()
                            .user(createWalletCommand.getUser())
                            .build()
            );
            if(!resNet.isOk())
                throw new Exception("Invalid user");
            WalletModel myWallet = mongoServiceWallet.createWallet(
                    WalletDomain.builder()
                            .user(createWalletCommand.getUser())
                            .cards(List.of())
                            .createdOn(LocalTime.now().toString())
                            .build()
            );
            return DataResponse.builder()
                    .ok(true)
                    .message("Wallet created ok")
                    .wallet(ModelMapper.entityToDomainWallet(myWallet))
                    .build();
        }catch (Exception e){
            return DataResponse.builder()
                    .ok(false)
                    .message("Unexpected error creating your wallet :/ -> " + e.getMessage())
                    .build();
        }
    }

    @Override
    public DataResponse createCard(CreateCardCommand createCardCommand) {
        try {
             Map<String, String> data = new HashMap<>();
             data.put("tuitionCard",CardActions.generateTuitionCard());
             data.put("nipCard",CardActions.generateNipCard());
             EncryptDataCommandResNet resNet = authServiceFeign.encryptData(
                     EncryptDataCommand.builder()
                             .data(data)
                             .build()
             );
             System.out.println(resNet + " net res");
             if(!resNet.isOk())
                 throw new Exception("Invalid data try again later :/");
             CardModel newCard = mongoServiceCard.createNewCard(
                    CardDomain.builder()
                    .tuitionCard(resNet.getData().get("tuitionCard"))
                    .nipCard(resNet.getData().get("nipCard"))
                    .mount(0)
                    .createdOn(LocalTime.now().toString())
                    .description(createCardCommand.getDescription())
                    .walledId(mongoServiceWallet.getWalletIdbyUsername(createCardCommand.getUser()))
                    .build()
            );
            Optional<WalletModel> myWallet = mongoServiceWallet.insertNewCardToWallet(createCardCommand.getUser(),ModelMapper.entityToDomainCard(newCard));
            if(myWallet.isEmpty())
                  throw new Exception("Invalid wallet");
            List<String> tempCards = myWallet.get().getCards();
            tempCards.add(newCard.getId());
            myWallet.get().setCards(tempCards);
            return DataResponse.builder()
                    .ok(true)
                    .message("Card created ok")
                    .wallet(ModelMapper.entityToDomainWallet(myWallet.get()))
                    .build();
        }catch (Exception e){
            return DataResponse.builder()
                .ok(false)
                .message("Unexpected error creating your card :/ -> " + e.getMessage())
                .wallet(null)
                .build();
        }
    }

    @Override
    public DataResponse deleteCard(DeleteCardCommand deleteCardCommand) {

        return null;
    }

    @Override
    public DataResponse getWallet(GetWalletCommand getWalletCommand) {
        try{
           Optional<WalletModel> walletData = mongoServiceWallet.getUserWallet(getWalletCommand.getUser());
            if(walletData.isEmpty())
                throw new Exception("wallet not found");
           WalletDomain myWallet = ModelMapper.entityToDomainWallet(walletData.get());
           /*
           List<CardDomain> cards = mongoServiceCard.getAllUserCards(walletData.get().getId())
                   .stream()
                   .map(ModelMapper::entityToDomainCard)
                   .toList();
            */
           return DataResponse.builder()
                   .ok(true)
                   .message("Wallet loaded ok")
                   .wallet(myWallet)
                   .build();
        }catch (Exception e){
           return DataResponse.builder()
               .ok(true)
               .message("Unexpected error loading data :/")
               .build();
        }
    }

    @Override
    public DataResponse updateCard(UpdateCardCommand updateCardCommand) {

        return null;
    }
}
