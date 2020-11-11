package com.excercise.paymentservice.domain.util

import com.excercise.paymentservice.domain.port.api.PaymentData
import com.excercise.paymentservice.domain.port.storing.Payment
import spock.lang.Specification

class PaymentFactoryTest extends Specification {

    PaymentFactory factoryUnderTest = new PaymentFactory(new PaymentIdGenerator())


    def "should correctly create a new payment"(){

        given:
        def paymentData = new PaymentData("ID-1", "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")

        when:
        def newPayment = factoryUnderTest.createNewPayment(paymentData)

        then:
        newPayment.id != null
        newPayment.userId == paymentData.userId
        newPayment.amount == paymentData.amount
        newPayment.currency == paymentData.currency
        newPayment.targetBankAccount == newPayment.targetBankAccount

    }


    def "should correctly create a payment view"(){

        given:
        def payment = new Payment("ID-1", "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")

        when:
        def paymentView = factoryUnderTest.createView(payment)

        then:
        paymentView.id == payment.id
        paymentView.userId == payment.userId
        paymentView.amount == payment.amount
        paymentView.currency == payment.currency
        paymentView.targetBankAccount == payment.targetBankAccount

    }

}
