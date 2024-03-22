package com.jwdev.ChallengePicPay.repositories;

import com.jwdev.ChallengePicPay.domain.transaction.Transaction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
