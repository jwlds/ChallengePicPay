package com.jwdev.ChallengePicPay.repositories;

import com.jwdev.ChallengePicPay.domain.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByEmail(String email);

}
