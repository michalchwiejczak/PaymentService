package com.excercise.paymentservice.domain

import com.excercise.paymentservice.domain.port.PaymentRepository
import com.excercise.paymentservice.domain.port.api.PaymentData
import com.excercise.paymentservice.domain.util.PaymentFactory
import com.excercise.paymentservice.domain.util.PaymentIdGenerator
import spock.lang.Specification

class CreatePaymentUseCaseTest extends Specification {

    def paymentRepositoryMock = Mock(PaymentRepository)
    def paymentFactory = new PaymentFactory(new PaymentIdGenerator())
    def createPaymentUseCaseUnderTest = new CreatePaymentUseCase(paymentFactory, paymentRepositoryMock)



    def "should create a new payment and return its ID"(){

        given:

        def paymentIdNull = null;
        def paymentData = new PaymentData(paymentIdNull, "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")
        paymentRepositoryMock.save(_) >> {arguments -> arguments[0].getAt("id")}

        when:
        def newPaymentId = createPaymentUseCaseUnderTest.execute(paymentData)

        then:
        newPaymentId != null

    }


}
