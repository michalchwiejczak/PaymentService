package com.excercise.paymentservice.domain;

import com.excercise.paymentservice.domain.port.PaymentRepository;
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException;
import com.excercise.paymentservice.domain.port.storing.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePaymentUseCase {

    private final PaymentRepository paymentRepository;


    public void execute(String paymentId){

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));

        paymentRepository.delete(payment.getId());
    }

}
