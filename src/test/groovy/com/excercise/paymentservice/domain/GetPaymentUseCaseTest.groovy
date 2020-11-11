package com.excercise.paymentservice.domain

import com.excercise.paymentservice.domain.port.PaymentRepository
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException
import com.excercise.paymentservice.domain.port.storing.Payment
import com.excercise.paymentservice.domain.util.PaymentFactory
import com.excercise.paymentservice.domain.util.PaymentIdGenerator
import spock.lang.Specification

class GetPaymentUseCaseTest extends Specification {

    def paymentRepositoryMock = Mock(PaymentRepository)
    def paymentFactory = new PaymentFactory(new PaymentIdGenerator())
    def getPaymentUseCaseUnderTest = new GetPaymentUseCase(paymentFactory, paymentRepositoryMock)


    def "should return a payment" () {

        def paymentId = "ID-1"
        given:
        def payment = new Payment(paymentId, "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")
        paymentRepositoryMock.findById(paymentId) >> Optional.of(payment)

        when:
        def paymentView = getPaymentUseCaseUnderTest.execute(paymentId)

        then:
        paymentView == paymentFactory.createView(payment)

    }



    def "Should throw an PaymentNotFoundException when a payment is not found"(){

        given:
        def paymentId = "ID-1"
        paymentRepositoryMock.findById(paymentId) >> Optional.empty()

        when:
        getPaymentUseCaseUnderTest.execute(paymentId)

        then:
        thrown(PaymentNotFoundException)

    }

}
