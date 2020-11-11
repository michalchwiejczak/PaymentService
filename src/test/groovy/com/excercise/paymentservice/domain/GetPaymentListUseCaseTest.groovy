package com.excercise.paymentservice.domain

import com.excercise.paymentservice.domain.port.PaymentRepository
import com.excercise.paymentservice.domain.port.storing.Payment
import com.excercise.paymentservice.domain.util.PaymentFactory
import com.excercise.paymentservice.domain.util.PaymentIdGenerator
import spock.lang.Specification

class GetPaymentListUseCaseTest extends Specification {

    def paymentRepositoryMock = Mock(PaymentRepository)
    def paymentFactory = new PaymentFactory(new PaymentIdGenerator())
    def getPaymentListUseCaseUnderTest = new GetPaymentListUseCase(paymentFactory, paymentRepositoryMock)



    def "should return a list of payments" (){

        given:
        def payment = new Payment("ID-1", "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")
        def anotherPayment = new Payment("ID-2", "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")
        paymentRepositoryMock.findAll() >> [payment, anotherPayment]

        when:
        def paymentList = getPaymentListUseCaseUnderTest.execute()

        then:
        paymentList.size() == 2
        paymentList.contains(paymentFactory.createView(payment))
        paymentList.contains(paymentFactory.createView(anotherPayment))

    }


}
