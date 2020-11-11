package com.excercise.paymentservice.adapter.api.response;

import com.excercise.paymentservice.domain.port.api.PaymentView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class GetPaymentListResponse {

    private final List<PaymentView> payments;
}
