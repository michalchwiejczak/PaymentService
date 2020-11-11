package com.excercise.paymentservice.adapter.database;

import com.excercise.paymentservice.domain.port.PaymentRepository;
import com.excercise.paymentservice.domain.port.storing.Payment;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@ConditionalOnProperty(prefix = "application.engine", name = "type", havingValue = "DATABASE")
public class MockDatabasePaymentRepository implements PaymentRepository {

    @Override
    public Optional<Payment> findById(String paymentId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Payment> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String save(Payment payment) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void update(Payment payment) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(String paymentId) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
