package com.excercise.paymentservice.domain.util;

import com.excercise.paymentservice.domain.port.api.PaymentData;
import com.excercise.paymentservice.domain.port.api.PaymentView;
import com.excercise.paymentservice.domain.port.storing.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentFactory {

    private final PaymentIdGenerator paymentIdGenerator;


    public Payment createNewPayment(PaymentData paymentData) {
        return new Payment(
                paymentIdGenerator.getId(),
                paymentData.getUserId(),
                paymentData.getAmount(),
                paymentData.getCurrency(),
                paymentData.getTargetBankAccount());
    }


    public PaymentView createView(Payment payment) {
        return new PaymentView(
                payment.getId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getTargetBankAccount());
    }

}
