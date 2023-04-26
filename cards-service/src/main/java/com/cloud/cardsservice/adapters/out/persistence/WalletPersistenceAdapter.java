package com.cloud.cardsservice.adapters.out.persistence;

import com.cloud.cardsservice.adapters.out.model.CardModel;
import com.cloud.cardsservice.adapters.out.model.WalletModel;
import com.cloud.cardsservice.adapters.out.repository.CardRepository;
import com.cloud.cardsservice.adapters.out.repository.WalletRepository;
import com.cloud.cardsservice.application.port.config.PersistenceAdapter;
import com.cloud.cardsservice.application.port.in.commands.*;
import com.cloud.cardsservice.application.port.out.LoadWalletData;
import com.cloud.cardsservice.application.port.out.commands.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@PersistenceAdapter
public class WalletPersistenceAdapter implements LoadWalletData {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public DataResponse createCard(CreateCardCommand createCardCommand) {
        try {
            Function<String, String> getWalletId = data -> {
                var n = walletRepository.findByUser(createCardCommand.getUser());
                return n.map(WalletModel::getId).orElseThrow();
            };
            CardModel newCard = CardModel.builder()
                    .tuitionCard("esucuela")
                    .nipCard("2345434")
                    .mount(999)
                    .createdOn(new Date().toString())
                    .description(createCardCommand.getDescription())
                    .walledId(getWalletId.apply(createCardCommand.getUser()))
                    .build();
            cardRepository.insert(newCard);
            Update updateDef = new Update();
            Query query = new Query();
            query.addCriteria(Criteria.where("user").is(createCardCommand.getUser()));
            updateDef.push("cards",newCard.getId());
            mongoTemplate.findAndModify(query, updateDef, WalletModel.class);
            return DataResponse.builder()
                    .ok(true)
                    .message("Card created ok")
                    .wallet(null)
                    .build();
        }catch (Exception e){
            System.out.println(e + " meme");
            return DataResponse.builder()
                .ok(true)
                .message("Card error ok")
                .wallet(null)
                .build();
        }
    }

    @Override
    public DataResponse createWallet(CreateWalletCommand createWalletCommand) {
        try {
            walletRepository.insert(
                    WalletModel.builder()
                            .user(createWalletCommand.getUser())
                            .cards(List.of())
                            .build()
            );
            return DataResponse.builder()
                    .ok(true)
                    .message("ok")
                    .build();
        }catch (Exception e){
            return DataResponse.builder().build();
        }
    }

    @Override
    public DataResponse deleteCard(DeleteCardCommand deleteCardCommand) {

        return null;
    }

    @Override
    public DataResponse getWallet(GetWalletCommand getWalletCommand) {
        try{
            System.out.println(walletRepository.findAll());
            System.out.println(cardRepository.findAll());
           return DataResponse.builder().build();
        }catch (Exception e){
           return DataResponse.builder().build();
        }
    }

    @Override
    public DataResponse updateCard(UpdateCardCommand updateCardCommand) {

        return null;
    }
}
