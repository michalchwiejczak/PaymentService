package com.excercise.paymentservice.domain;

import com.excercise.paymentservice.domain.port.PaymentRepository;
import com.excercise.paymentservice.domain.port.api.PaymentData;
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException;
import com.excercise.paymentservice.domain.port.storing.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePaymentUseCase {

    private final PaymentRepository paymentRepository;


    public void execute(PaymentData paymentData){

        Payment payment = paymentRepository.findById(paymentData.getId())
                .orElseThrow(() -> new PaymentNotFoundException(paymentData.getId()));

        payment.update(
                payment.getUserId(),
                paymentData.getAmount(),
                paymentData.getCurrency(),
                paymentData.getTargetBankAccount());

        paymentRepository.update(payment);
    }
}
