package com.excercise.paymentservice.configuration.domain;

import com.excercise.paymentservice.domain.*;
import com.excercise.paymentservice.domain.port.PaymentRepository;
import com.excercise.paymentservice.domain.util.PaymentFactory;
import com.excercise.paymentservice.domain.util.PaymentIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public PaymentIdGenerator paymentIdGenerator(){
        return new PaymentIdGenerator();
    }

    @Bean
    public PaymentFactory paymentFactory(PaymentIdGenerator paymentIdGenerator){
        return new PaymentFactory(paymentIdGenerator);
    }

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentFactory paymentFactory, PaymentRepository paymentRepository){
        return new GetPaymentUseCase(paymentFactory, paymentRepository);
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(PaymentFactory paymentFactory, PaymentRepository paymentRepository){
        return new CreatePaymentUseCase(paymentFactory, paymentRepository);
    }

    @Bean
    public UpdatePaymentUseCase updatePaymentUseCase(PaymentRepository paymentRepository){
        return new UpdatePaymentUseCase(paymentRepository);
    }

    @Bean
    public DeletePaymentUseCase deletePaymentUseCase(PaymentRepository paymentRepository){
        return new DeletePaymentUseCase(paymentRepository);
    }

    @Bean
    public GetPaymentListUseCase getPaymentListUseCase(PaymentFactory paymentFactory, PaymentRepository paymentRepository){
        return new GetPaymentListUseCase(paymentFactory, paymentRepository);
    }

}
