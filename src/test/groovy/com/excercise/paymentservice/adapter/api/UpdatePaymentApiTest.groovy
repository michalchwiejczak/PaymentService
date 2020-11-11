package com.excercise.paymentservice.adapter.api

import com.excercise.paymentservice.Utils
import com.excercise.paymentservice.adapter.api.request.UpdatePaymentRequest
import com.excercise.paymentservice.configuration.PaymentServiceTestConfiguration
import com.excercise.paymentservice.domain.UpdatePaymentUseCase
import com.excercise.paymentservice.domain.port.api.PaymentData
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles(value = "test")
@SpringBootTest(classes = PaymentServiceTestConfiguration)
@AutoConfigureMockMvc
class UpdatePaymentApiTest extends Specification{

    @Autowired MockMvc mockMvc
    @Autowired UpdatePaymentUseCase updatePaymentUseCaseMock



    def "updating payment should return status 204 NO_CONTENT when the operation is successful"(){

        given:
        def paymentId = "ID-1"
        def paymentData = new PaymentData(paymentId, "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")

        when:
        def result = mockMvc.perform(put("/payment").content(requestForPaymentData(paymentData)).contentType(MediaType.APPLICATION_JSON))

        then:
        result
                .andDo(print())
                .andExpect(status().isNoContent())
    }



    def "updating payment should return status 404 NOT_FOUND when payment was not found"(){

        given:
        def paymentId = "ID-1"
        def paymentData = new PaymentData(paymentId, "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")
        updatePaymentUseCaseMock.execute(paymentData) >> { throw new PaymentNotFoundException(paymentId) }

        when:
        def result = mockMvc.perform(put("/payment").content(requestForPaymentData(paymentData)).contentType(MediaType.APPLICATION_JSON))

        then:
        result
                .andDo(print())
                .andExpect(status().isNotFound())
    }



    def "updating payment should return status 400 BAD_REQUEST when incorrect data was provided"(){

        given:
        UpdatePaymentRequest requestWithoutPaymentData = new UpdatePaymentRequest(null,null, null, null, null)

        when:
        def result = mockMvc.perform(put("/payment").content(Utils.getAsJsonString(requestWithoutPaymentData)).contentType(MediaType.APPLICATION_JSON))

        then:
        result
                .andDo(print())
                .andExpect(status().isBadRequest())
    }



    private String requestForPaymentData(PaymentData payment){
        UpdatePaymentRequest request = new UpdatePaymentRequest(
                payment.getId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getTargetBankAccount())
        return Utils.getAsJsonString(request)
    }

}
