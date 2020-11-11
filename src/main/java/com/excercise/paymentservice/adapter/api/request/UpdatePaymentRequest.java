package com.excercise.paymentservice.adapter.api.request;

import com.excercise.paymentservice.domain.port.api.PaymentData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UpdatePaymentRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String userId;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String currency;

    @NotBlank
    private String targetBankAccount;


    public PaymentData toPaymentData(){
        return PaymentData.builder()
                .id(id)
                .userId(userId)
                .amount(amount)
                .currency(currency)
                .targetBankAccount(targetBankAccount)
                .build();
    }


}
