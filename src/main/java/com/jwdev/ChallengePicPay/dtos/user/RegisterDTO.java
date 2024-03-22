package com.jwdev.ChallengePicPay.dtos.user;

import com.jwdev.ChallengePicPay.domain.user.UserType;

import java.math.BigDecimal;

public record RegisterDTO(String email, String password, String document, String fullName, BigDecimal balance, UserType role) {
}
