package com.jwdev.ChallengePicPay.services.transaction;

import com.jwdev.ChallengePicPay.domain.transaction.Transaction;
import com.jwdev.ChallengePicPay.domain.user.User;
import com.jwdev.ChallengePicPay.dtos.transactions.TransactionDTO;
import com.jwdev.ChallengePicPay.repositories.TransactionRepository;
import com.jwdev.ChallengePicPay.repositories.UserRepository;
import com.jwdev.ChallengePicPay.services.user.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.management.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MongoTemplate mongoTemplate;


    public Transaction createTransaction(TransactionDTO payload) throws Exception{
        User sender = this.userService.findUserById(payload.senderId());

        if (sender.getBalance().compareTo(payload.value()) < 0) {
            throw new Exception("Insufficient balance to perform the transaction");
        }
        User receiver = this.userService.findUserById(payload.receiveId());

        boolean isAuthorization = this.authorizeTransaction(sender,payload.value());
        if(!isAuthorization) { throw new Exception("unauthorized betrayal");  }
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(payload.value());
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(payload.value()));
        receiver.setBalance(receiver.getBalance().add(payload.value()));
        this.transactionRepository.insert(transaction);





        mongoTemplate.updateFirst(query(where("_id").is(new ObjectId(sender.getId()))), update("balance", sender.getBalance()), User.class);
        mongoTemplate.updateFirst(query(where("_id").is(new ObjectId(receiver.getId()))), update("balance", receiver.getBalance()), User.class);

        this.userRepository.save(sender);
        this.userRepository.save(receiver);

        this.notificationService.sendNotification(sender,"Transition sent successfully");
        this.notificationService.sendNotification(receiver,"Transition received successfully");


        return transaction;
    }


     private boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return message.equals("Autorizado");
        }
        return false;
     }

}
