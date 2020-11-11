package com.excercise.paymentservice.adapter.api.error;

import lombok.Getter;

@Getter
class ValidationErrorDetail extends ErrorDetail {

        private String object;
        private String field;
        private Object rejectedValue;
        private String message;


    ValidationErrorDetail(String object, String message) {
        this.object = object;
        this.message = message;
        this.field = null;
        this.rejectedValue = null;
    }

    ValidationErrorDetail(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
