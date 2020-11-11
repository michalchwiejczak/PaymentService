package com.excercise.paymentservice.domain;

import com.excercise.paymentservice.domain.port.PaymentRepository;
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException;
import com.excercise.paymentservice.domain.port.api.PaymentView;
import com.excercise.paymentservice.domain.port.storing.Payment;
import com.excercise.paymentservice.domain.util.PaymentFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetPaymentUseCase {

    private final PaymentFactory paymentFactory;
    private final PaymentRepository paymentRepository;

    public PaymentView execute(String paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));

        PaymentView paymentView = paymentFactory.createView(payment);

        return paymentView;
    }
}
