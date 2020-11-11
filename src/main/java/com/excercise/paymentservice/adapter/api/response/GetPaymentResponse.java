package com.excercise.paymentservice.adapter.api.response;

import com.excercise.paymentservice.domain.port.api.PaymentView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class GetPaymentResponse {

    private final PaymentView payment;
}
