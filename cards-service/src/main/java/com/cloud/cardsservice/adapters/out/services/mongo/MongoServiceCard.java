package com.cloud.cardsservice.adapters.out.services.mongo;

import com.cloud.cardsservice.adapters.in.utils.CardActions;
import com.cloud.cardsservice.adapters.out.model.CardModel;
import com.cloud.cardsservice.adapters.out.model.WalletModel;
import com.cloud.cardsservice.adapters.out.repository.CardRepository;
import com.cloud.cardsservice.adapters.out.repository.WalletRepository;
import com.cloud.cardsservice.domain.CardDomain;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class MongoServiceCard {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public CardModel createNewCard(CardDomain cardDomain){
        CardModel newCard = CardModel.builder()
                .tuitionCard(cardDomain.getTuitionCard())
                .nipCard(cardDomain.getNipCard())
                .mount(cardDomain.getMount())
                .createdOn(cardDomain.getCreatedOn())
                .description(cardDomain.getDescription())
                .walledId(cardDomain.getWalledId())
                .build();
        return cardRepository.insert(newCard);
    }

    public List<CardModel> getAllUserCards(String walledId){
        return cardRepository.findByWalledId(walledId);
    }

}
