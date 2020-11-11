package com.excercise.paymentservice.domain.port;

import com.excercise.paymentservice.domain.port.storing.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    Optional<Payment> findById(String paymentId);
    List<Payment> findAll();
    String save(Payment payment);
    void update(Payment payment);
    void delete(String paymentId);
}
