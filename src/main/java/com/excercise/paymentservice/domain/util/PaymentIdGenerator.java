package com.excercise.paymentservice.domain.util;

import java.util.UUID;


public class PaymentIdGenerator {

    String getId(){
        return UUID.randomUUID().toString();
    }

}
