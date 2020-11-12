package com.excercise.paymentservice.adapter.csv;

import com.excercise.paymentservice.domain.port.PaymentRepository;
import com.excercise.paymentservice.domain.port.storing.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(prefix = "application.engine", name = "type", havingValue = "CSV")
@RequiredArgsConstructor
public class CsvPaymentRepository implements PaymentRepository {

    //an alternative to thread-level locking can be
    // FileChannel class mechanism but it isn't portable
    private final ReentrantLock mutex = new ReentrantLock();
    private final CsvFileHandler csvFileHandler;
    private final CsvPaymentFactory csvPaymentFactory;


    @Override
    public Optional<Payment> findById(String paymentId) {
        try{
            mutex.lock();
            return csvFileHandler.findById(paymentId)
                    .map(record -> csvPaymentFactory.toPayment(record));
        }
        finally {
            mutex.unlock();
        }
    }


    @Override
    public List<Payment> findAll() {
        try{
            mutex.lock();
            return csvFileHandler.findAll().stream()
                    .map(record -> csvPaymentFactory.toPayment(record))
                    .collect(Collectors.toList());
        }
        finally {
            mutex.unlock();
        }
    }


    @Override
    public String save(Payment payment) {
        try{
            mutex.lock();
            csvFileHandler.save(csvPaymentFactory.toCsvValues(payment));
            return payment.getId();
        }
        finally {
            mutex.unlock();
        }
    }


    @Override
    public void update(Payment payment) {
        try{
            mutex.lock();
            csvFileHandler.update(payment.getId(), csvPaymentFactory.toCsvValues(payment));
        }
        finally {
            mutex.unlock();
        }

    }


    @Override
    public void delete(String paymentId) {
        try{
            mutex.lock();
            csvFileHandler.delete(paymentId);
        }
        finally {
            mutex.unlock();
        }

    }

}
