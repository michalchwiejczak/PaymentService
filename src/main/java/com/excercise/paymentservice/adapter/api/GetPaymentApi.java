package com.excercise.paymentservice.adapter.api;

import com.excercise.paymentservice.adapter.api.response.GetPaymentResponse;
import com.excercise.paymentservice.domain.GetPaymentUseCase;
import com.excercise.paymentservice.domain.port.api.PaymentView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPaymentApi {

    private final GetPaymentUseCase getPaymentUseCase;


    @ApiOperation("Gets payment with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Payment not found.")
    })
    @GetMapping("/payment/{id}")
    ResponseEntity<GetPaymentResponse> getPayment(@PathVariable String id){
        PaymentView paymentView = getPaymentUseCase.execute(id);
        GetPaymentResponse response = new GetPaymentResponse(paymentView);
        return ResponseEntity.ok(response);
    }

}
