package com.excercise.paymentservice.domain

import com.excercise.paymentservice.domain.port.PaymentRepository
import com.excercise.paymentservice.domain.port.api.PaymentData
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException
import com.excercise.paymentservice.domain.port.storing.Payment
import spock.lang.Specification

class UpdatePaymentUseCaseTest extends Specification {

    def paymentRepositoryMock = Mock(PaymentRepository)
    def updatePaymentUseCaseUnderTest = new UpdatePaymentUseCase(paymentRepositoryMock)


    def "Should correctly update the payment"(){

        given:
        def paymentId = "ID-1"
        def paymentData = new PaymentData(paymentId, "USER-ID", BigDecimal.ONE, "CHF", "IBAN-1")
        def payment = toPayment(paymentData)

        when:
        updatePaymentUseCaseUnderTest.execute(paymentData)

        then:
        1 * paymentRepositoryMock.findById(paymentId) >> Optional.of(payment)
        1 * paymentRepositoryMock.update(payment)

    }


    def "Should throw an PaymentNotFoundException when a payment is not found"(){

        given:
        def paymentId = "ID-1"
        def paymentData = new PaymentData(paymentId, "USER-ID", BigDecimal.ONE, "CHF", "IBAN-1")
        paymentRepositoryMock.findById(paymentId) >> Optional.empty()

        when:
        updatePaymentUseCaseUnderTest.execute(paymentData)

        then:
        thrown(PaymentNotFoundException)

    }

    private Payment toPayment(PaymentData paymentData){
        return new Payment(
                paymentData.getId(),
                paymentData.getUserId(),
                paymentData.getAmount(),
                paymentData.getCurrency(),
                paymentData.getTargetBankAccount()
        )
    }


}
