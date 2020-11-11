package com.excercise.paymentservice.domain.port.api;

public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException(String paymentId) {
        super("Payment not found for id: " + paymentId);
    }
}
