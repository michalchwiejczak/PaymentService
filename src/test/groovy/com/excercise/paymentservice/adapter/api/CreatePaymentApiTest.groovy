package com.excercise.paymentservice.adapter.api

import com.excercise.paymentservice.Utils
import com.excercise.paymentservice.adapter.api.request.CreatePaymentRequest
import com.excercise.paymentservice.configuration.PaymentServiceTestConfiguration
import com.excercise.paymentservice.domain.CreatePaymentUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles(value = "test")
@SpringBootTest(classes = PaymentServiceTestConfiguration)
@AutoConfigureMockMvc
class CreatePaymentApiTest extends Specification {

    @Autowired MockMvc mockMvc
    @Autowired CreatePaymentUseCase createPaymentUseCaseMock


    def "creating payment should return status 201 CREATED and new resource Location for successfully created payment"(){

        given:
        def newPaymentId = "ID-1"
        CreatePaymentRequest request = new CreatePaymentRequest("USERID-1", BigDecimal.ONE, "USD", "IBAN-1")
        createPaymentUseCaseMock.execute(_) >> newPaymentId

        when:
        def result = mockMvc.perform(post("/payment").content(Utils.getAsJsonString(request)).contentType(MediaType.APPLICATION_JSON))

        then:
        result
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/payment/" + newPaymentId))

    }



    def "creating payment should return status 400 BAD_REQUEST when incorrect data was provided"(){

        given:
        CreatePaymentRequest requestWithoutPaymentData = new CreatePaymentRequest(null, null, null, null)

        when:
        def result = mockMvc.perform(post("/payment").content(Utils.getAsJsonString(requestWithoutPaymentData)).contentType(MediaType.APPLICATION_JSON))

        then:
        result
                .andDo(print())
                .andExpect(status().isBadRequest())
    }

}
