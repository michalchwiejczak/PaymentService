package com.excercise.paymentservice.adapter.api;

import com.excercise.paymentservice.adapter.api.request.UpdatePaymentRequest;
import com.excercise.paymentservice.domain.UpdatePaymentUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UpdatePaymentApi {

    private final UpdatePaymentUseCase updatePaymentUseCase;


    @ApiOperation("Updates the payment with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Payment updated. No content returned."),
            @ApiResponse(code = 404, message = "Payment not found.")
    })
    @PutMapping("/payment")
    ResponseEntity updatePayment(@RequestBody @Valid UpdatePaymentRequest request){
        updatePaymentUseCase.execute(request.toPaymentData());
        return ResponseEntity.noContent().build();
    }

}
