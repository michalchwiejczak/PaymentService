package com.excercise.paymentservice.adapter.api

import com.excercise.paymentservice.configuration.PaymentServiceTestConfiguration
import com.excercise.paymentservice.domain.DeletePaymentUseCase
import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles(value = "test")
@SpringBootTest(classes = PaymentServiceTestConfiguration)
@AutoConfigureMockMvc
class DeletePaymentApiTest extends Specification{

    @Autowired MockMvc mockMvc
    @Autowired DeletePaymentUseCase deletePaymentUseCaseMock



    def "deleting payment should return status 204 NO_CONTENT when the operation is successful"(){

        given:
        def paymentId = "ID-1"

        when:
        def result = mockMvc.perform(delete("/payment/{id}", paymentId))

        then:
        result
                .andDo(print())
                .andExpect(status().isNoContent())
    }



    def "deleting payment should return status 404 NOT_FOUND when payment was not found"(){

        given:
        def paymentId = "ID-1"
        deletePaymentUseCaseMock.execute(paymentId) >> { throw new PaymentNotFoundException(paymentId) }

        when:
        def result = mockMvc.perform(delete("/payment/{id}", paymentId))

        then:
        result
                .andDo(print())
                .andExpect(status().isNotFound())
    }

}
