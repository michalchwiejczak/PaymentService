package com.excercise.paymentservice.adapter.api;

import com.excercise.paymentservice.adapter.api.request.CreatePaymentRequest;
import com.excercise.paymentservice.domain.CreatePaymentUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CreatePaymentApi {

    private final CreatePaymentUseCase createPaymentUseCase;


    @ApiOperation("Creates a new payment.")
    @ApiResponse(code = 201, message = "Payment Created.")
    @PostMapping("/payment")
    ResponseEntity createPayment(@RequestBody @Valid CreatePaymentRequest request){
        String paymentId = createPaymentUseCase.execute(request.toPaymentData());
        URI location = URI.create("/payment/" + paymentId);
        return ResponseEntity.created(location).build();
    }

}
