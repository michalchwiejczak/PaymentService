package com.excercise.paymentservice.adapter.api.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
class Error {

    private HttpStatus status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private LocalDateTime timestamp;
    private List<ErrorDetail> details;


    Error(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }


    void addDetails(List<? extends ErrorDetail> details){
        if(this.details == null){
            this.details = new ArrayList<>();
        }
        this.details.addAll(details);
    }


}
