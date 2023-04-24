package com.cloud.authservice.adapters.out.repository;

import com.cloud.authservice.adapters.out.model.AuthUserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends MongoRepository<AuthUserModel, ObjectId> {
    Optional<AuthUserModel> findByUsername(String username);
}
