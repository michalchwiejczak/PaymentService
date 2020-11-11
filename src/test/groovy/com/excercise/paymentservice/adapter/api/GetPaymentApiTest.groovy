package com.excercise.paymentservice.adapter.api

import com.excercise.paymentservice.Utils
import com.excercise.paymentservice.adapter.api.response.GetPaymentResponse
import com.excercise.paymentservice.configuration.PaymentServiceTestConfiguration
import com.excercise.paymentservice.domain.GetPaymentUseCase
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException
import com.excercise.paymentservice.domain.port.api.PaymentView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles(value = "test")
@SpringBootTest(classes = PaymentServiceTestConfiguration)
@AutoConfigureMockMvc
class GetPaymentApiTest extends Specification {

    @Autowired MockMvc mockMvc
    @Autowired GetPaymentUseCase getPaymentUseCaseMock


    def "getting payment should return status 200 OK and payment data when the operation is successful"(){

        given:
        def paymentId = "ID-1"
        def paymentView = new PaymentView(paymentId, "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")
        getPaymentUseCaseMock.execute(paymentId) >> paymentView

        when:
        def result = mockMvc.perform(get("/payment/{id}", paymentId))

        then:
        result
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResponse(paymentView)))

    }



    def "getting payment should return status 404 NOT_FOUND when payment was not found"(){

        given:
        def paymentId = "ID-1"
        getPaymentUseCaseMock.execute(paymentId) >> { throw new PaymentNotFoundException(paymentId) }

        when:
        def result = mockMvc.perform(get("/payment/{id}", paymentId))

        then:
        result
                .andDo(print())
                .andExpect(status().isNotFound())
    }



    private String expectedJsonResponse(PaymentView paymentView){
        GetPaymentResponse response = new GetPaymentResponse(paymentView)
        return Utils.getAsJsonString(response)
    }

}
