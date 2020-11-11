package com.excercise.paymentservice.domain.port.api;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class PaymentView {

    private String id;
    private String userId;
    private BigDecimal amount;
    private String currency;
    private String targetBankAccount;

}
