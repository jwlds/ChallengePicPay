package com.jwdev.ChallengePicPay.dtos.transactions;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, String senderId, String receiveId){

}
