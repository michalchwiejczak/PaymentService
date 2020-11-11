package com.excercise.paymentservice.domain.port.storing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Payment {

    private String id;
    private String userId;
    private BigDecimal amount;
    private String currency;
    private String targetBankAccount;


    public void update(String userId, BigDecimal amount, String currency, String targetBankAccount){
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.targetBankAccount = targetBankAccount;
    }

}
