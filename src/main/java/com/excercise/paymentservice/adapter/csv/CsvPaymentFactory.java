package com.excercise.paymentservice.adapter.csv;

import com.excercise.paymentservice.domain.port.storing.Payment;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CsvPaymentFactory {

    Payment toPayment(CSVRecord record){
        return new Payment(
                record.get(CsvPaymentFieldEnum.ID),
                record.get(CsvPaymentFieldEnum.USER_ID),
                new BigDecimal(record.get(CsvPaymentFieldEnum.AMOUNT)),
                record.get(CsvPaymentFieldEnum.CURRENCY),
                record.get(CsvPaymentFieldEnum.TARGET_BANK_ACCOUNT));
    }



    Object[] toCsvValues(Payment payment){
        return new String[]{
                payment.getId(),
                payment.getUserId(),
                payment.getAmount().toString(),
                payment.getCurrency(),
                payment.getTargetBankAccount()
        };
    }

}
