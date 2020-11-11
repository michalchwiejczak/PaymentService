package com.excercise.paymentservice.domain;

import com.excercise.paymentservice.domain.port.PaymentRepository;
import com.excercise.paymentservice.domain.port.api.PaymentData;
import com.excercise.paymentservice.domain.port.storing.Payment;
import com.excercise.paymentservice.domain.util.PaymentFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePaymentUseCase {

    private final PaymentFactory paymentFactory;
    private final PaymentRepository paymentRepository;


    public String execute(PaymentData paymentData){

        Payment newPayment = paymentFactory.createNewPayment(paymentData);

        String paymentId = paymentRepository.save(newPayment);

        return paymentId;
    }

}
