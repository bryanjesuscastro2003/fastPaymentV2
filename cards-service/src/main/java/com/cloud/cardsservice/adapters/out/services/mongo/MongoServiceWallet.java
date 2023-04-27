package com.cloud.cardsservice.adapters.out.services.mongo;

import com.cloud.cardsservice.adapters.out.model.WalletModel;
import com.cloud.cardsservice.adapters.out.repository.WalletRepository;
import com.cloud.cardsservice.domain.CardDomain;
import com.cloud.cardsservice.domain.WalletDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoServiceWallet {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public String getWalletIdbyUsername(String user){
        var n = walletRepository.findByUser(user);
        return n.map(WalletModel::getId).orElseThrow();
    }

    public Optional<WalletModel> getUserWallet(String user){
        return walletRepository.findByUser(user);
    }

    public Optional<WalletModel> insertNewCardToWallet(String user, CardDomain cardDomain){
        Update updateDef = new Update();
        Query query = new Query();
        query.addCriteria(Criteria.where("user").is(user));
        updateDef.push("cards",cardDomain.getId());
        return Optional.ofNullable(mongoTemplate.findAndModify(query, updateDef, WalletModel.class));
    }

    public WalletModel createWallet(WalletDomain walletDomain) throws Exception {
        if (walletRepository.findByUser(walletDomain.getUser()).isPresent()) {
            throw new Exception("Such user has already a wallet registered");
        }
        return walletRepository.insert(
                WalletModel.builder()
                        .user(walletDomain.getUser())
                        .cards(List.of())
                        .createdOn(walletDomain.getCreatedOn())
                        .build()
        );
    }



}
