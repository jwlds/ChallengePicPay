package com.jwdev.ChallengePicPay.domain.transaction;


import com.jwdev.ChallengePicPay.domain.user.User;
import com.jwdev.ChallengePicPay.dtos.transactions.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection="transactions")

public class Transaction {
    @Id
    private String id;
    private BigDecimal amount;
    @DBRef
    private User sender;
    @DBRef
    private User receiver;
    private LocalDateTime timestamp;




}
