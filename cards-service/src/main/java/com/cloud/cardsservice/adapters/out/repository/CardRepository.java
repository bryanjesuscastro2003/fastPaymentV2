package com.cloud.cardsservice.adapters.out.repository;

import com.cloud.cardsservice.adapters.out.model.CardModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends MongoRepository<CardModel, ObjectId> {

}
