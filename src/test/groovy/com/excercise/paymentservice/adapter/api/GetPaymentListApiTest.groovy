package com.excercise.paymentservice.adapter.api

import com.excercise.paymentservice.Utils
import com.excercise.paymentservice.adapter.api.response.GetPaymentListResponse
import com.excercise.paymentservice.configuration.PaymentServiceTestConfiguration
import com.excercise.paymentservice.domain.GetPaymentListUseCase
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
class GetPaymentListApiTest extends Specification{

    @Autowired MockMvc mockMvc
    @Autowired GetPaymentListUseCase getPaymentListUseCaseMock



    def "getting payment list should return status 200 OK and payments data when the operation is successful"(){

        given:
        def payment = new PaymentView("ID-1", "USERID-1", BigDecimal.ONE, "USD", "IBAN-1")
        def anotherPayment = new PaymentView("ID-2", "USERID-2", BigDecimal.TEN, "CHF", "IBAN-2")
        getPaymentListUseCaseMock.execute() >> [payment, anotherPayment]

        when:
        def result = mockMvc.perform(get("/payment/list"))

        then:
        result
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResponse([payment, anotherPayment])))

    }



    private String expectedJsonResponse(List<PaymentView> payments){
        return Utils.getAsJsonString(new GetPaymentListResponse(payments))
    }

}
