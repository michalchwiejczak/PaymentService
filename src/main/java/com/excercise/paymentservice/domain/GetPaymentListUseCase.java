package com.excercise.paymentservice.domain;

import com.excercise.paymentservice.domain.port.PaymentRepository;
import com.excercise.paymentservice.domain.port.api.PaymentView;
import com.excercise.paymentservice.domain.port.storing.Payment;
import com.excercise.paymentservice.domain.util.PaymentFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetPaymentListUseCase {

    private final PaymentFactory paymentFactory;
    private final PaymentRepository paymentRepository;

    public List<PaymentView> execute(){

        List<Payment> payments = paymentRepository.findAll();

        List<PaymentView> paymentViews = payments.stream()
                .map(payment -> paymentFactory.createView(payment))
                .collect(Collectors.toList());

        return paymentViews;
    }

}
