package com.cloud.cardsservice.adapters.out;

import com.cloud.cardsservice.adapters.out.model.CardModel;
import com.cloud.cardsservice.adapters.out.model.WalletModel;
import com.cloud.cardsservice.domain.CardDomain;
import com.cloud.cardsservice.domain.WalletDomain;

public class ModelMapper {

    public static CardDomain entityToDomainCard(CardModel cardModel){
         return CardDomain.builder()
                 .id(cardModel.getId())
                 .tuitionCard(cardModel.getTuitionCard())
                 .nipCard(cardModel.getNipCard())
                 .description(cardModel.getDescription())
                 .mount(cardModel.getMount())
                 .createdOn(cardModel.getCreatedOn())
                 .build();
    }

    public static CardModel domainToEntityCard(CardDomain cardDomain){
        return CardModel.builder()
                .id(cardDomain.getId())
                .tuitionCard(cardDomain.getTuitionCard())
                .nipCard(cardDomain.getNipCard())
                .description(cardDomain.getDescription())
                .mount(cardDomain.getMount())
                .createdOn(cardDomain.getCreatedOn())
                .build();
    }

    public static WalletModel domainToEntityWallet(WalletDomain walletDomain){
         return WalletModel.builder()
                 .id(walletDomain.getId())
                 .cards(walletDomain.getCards())
                 .createdOn(walletDomain.getCreatedOn())
                 .build();
    }

    public static WalletDomain entityToDomainWallet(WalletModel walletModel){
            return WalletDomain.builder()
                    .id(walletModel.getId())
                    .user(walletModel.getUser())
                    .cards(walletModel.getCards())
                    .createdOn(walletModel.getCreatedOn())
                    .build();
    }



}
