package com.cloud.cardsservice.adapters.out.repository;

import com.cloud.cardsservice.adapters.out.model.CardModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends MongoRepository<CardModel, ObjectId> {
    List<CardModel> findByWalledId(String walledId);
}
