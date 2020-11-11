package com.excercise.paymentservice.domain

import com.excercise.paymentservice.domain.port.PaymentRepository
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException
import com.excercise.paymentservice.domain.port.storing.Payment
import spock.lang.Specification

class DeletePaymentUseCaseTest extends Specification {

    def paymentRepositoryMock = Mock(PaymentRepository)
    def deletePaymentUseCaseUnderTest = new DeletePaymentUseCase(paymentRepositoryMock)



    def "Should correctly delete the payment"(){

        given:
        def paymentId = "ID-1"
        def payment = new Payment(paymentId, "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")

        when:
        deletePaymentUseCaseUnderTest.execute(paymentId)

        then:
        1 * paymentRepositoryMock.findById(paymentId) >> Optional.of(payment)
        1 * paymentRepositoryMock.delete(paymentId)

    }



    def "Should throw an PaymentNotFoundException when a payment is not found"(){

        given:
        def paymentId = "ID-1"
        paymentRepositoryMock.findById(paymentId) >> Optional.empty()

        when:
        deletePaymentUseCaseUnderTest.execute(paymentId)

        then:
        thrown(PaymentNotFoundException)

    }

}
