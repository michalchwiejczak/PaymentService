package com.excercise.paymentservice.adapter.api;

import com.excercise.paymentservice.domain.DeletePaymentUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeletePaymentApi {

    private final DeletePaymentUseCase deletePaymentUseCase;


    @ApiOperation("Deletes the payment with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Payment deleted. No content returned."),
            @ApiResponse(code = 404, message = "Payment not found.")
    })
    @DeleteMapping("/payment/{id}")
    ResponseEntity deletePayment(@PathVariable String id){
        deletePaymentUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
