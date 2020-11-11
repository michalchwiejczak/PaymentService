package com.excercise.paymentservice.domain.port.api;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PaymentData {

    private String id;
    private String userId;
    private BigDecimal amount;
    private String currency;
    private String targetBankAccount;

}
