package com.excercise.paymentservice.configuration

import com.excercise.paymentservice.domain.*
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import spock.mock.DetachedMockFactory

import java.util.concurrent.locks.ReentrantLock

@TestConfiguration
class PaymentServiceTestConfiguration {

    DetachedMockFactory detachedMockFactory = new DetachedMockFactory();

    @Bean
    @Primary
    ReentrantLock reentrantLock(){
        return new ReentrantLock();
    }

    @Bean
    @Primary
    CreatePaymentUseCase createPaymentUseCaseMock(){
        return detachedMockFactory.Stub(CreatePaymentUseCase)
    }

    @Bean
    @Primary
    DeletePaymentUseCase deletePaymentUseCaseMock(){
        return detachedMockFactory.Stub(DeletePaymentUseCase)
    }

    @Bean
    @Primary
    GetPaymentListUseCase getPaymentListUseCaseMock(){
        return detachedMockFactory.Stub(GetPaymentListUseCase)
    }

    @Bean
    @Primary
    GetPaymentUseCase getPaymentUseCaseMock(){
        return detachedMockFactory.Stub(GetPaymentUseCase)
    }

    @Bean
    @Primary
    UpdatePaymentUseCase updatePaymentUseCaseMock(){
        return detachedMockFactory.Stub(UpdatePaymentUseCase)
    }

}
