package com.excercise.paymentservice.adapter.api;

import com.excercise.paymentservice.adapter.api.response.GetPaymentListResponse;
import com.excercise.paymentservice.domain.GetPaymentListUseCase;
import com.excercise.paymentservice.domain.port.api.PaymentView;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetPaymentListApi {

    private final GetPaymentListUseCase getPaymentListUseCase;

    @ApiOperation("Gets all payments")
    @GetMapping("/payment/list")
    ResponseEntity<GetPaymentListResponse> getPaymentList(){
        List<PaymentView> paymentViews = getPaymentListUseCase.execute();
        GetPaymentListResponse response = new GetPaymentListResponse(paymentViews);
        return ResponseEntity.ok(response);
    }

}
