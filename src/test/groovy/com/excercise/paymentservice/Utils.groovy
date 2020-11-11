package com.excercise.paymentservice

import com.fasterxml.jackson.databind.ObjectMapper

class Utils {

    static String getAsJsonString(Object object){
        ObjectMapper mapper = new ObjectMapper()
        return mapper.writeValueAsString(object)
    }

}
