package com.cloud.cardsservice.adapters.out.repository;

import com.cloud.cardsservice.adapters.out.model.WalletModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends MongoRepository<WalletModel, ObjectId> {
     Optional<WalletModel> findByUser(String user);
}

