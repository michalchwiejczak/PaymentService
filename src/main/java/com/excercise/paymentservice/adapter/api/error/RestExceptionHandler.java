package com.excercise.paymentservice.adapter.api.error;


import com.excercise.paymentservice.domain.port.api.PaymentNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PaymentNotFoundException.class)
    protected ResponseEntity<Object> handlePaymentNotFound(PaymentNotFoundException exception){

        Error error = new Error(HttpStatus.NOT_FOUND, exception.getMessage());

        return createResponseEntity(error);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Error error = new Error(HttpStatus.BAD_REQUEST, "Validation error");

        List<ValidationErrorDetail> fieldErrorDetails =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(field -> createtFieldValidationErrorDetail(field))
                        .collect(Collectors.toList());
        error.addDetails(fieldErrorDetails);

        List<ValidationErrorDetail> globalErrorDetails = ex.getBindingResult().getGlobalErrors().stream()
                .map(global -> createtGlobalValidationErrorDetail(global))
                .collect(Collectors.toList());
        error.addDetails(globalErrorDetails);


        return createResponseEntity(error);
    }



    private ValidationErrorDetail createtFieldValidationErrorDetail(org.springframework.validation.FieldError field) {
        return new ValidationErrorDetail(field.getObjectName(), field.getField(), field.getRejectedValue(), field.getDefaultMessage());
    }

    private ValidationErrorDetail createtGlobalValidationErrorDetail(ObjectError error) {
        return new ValidationErrorDetail(error.getObjectName(), error.getDefaultMessage());
    }

    private ResponseEntity<Object> createResponseEntity(Error error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

}
