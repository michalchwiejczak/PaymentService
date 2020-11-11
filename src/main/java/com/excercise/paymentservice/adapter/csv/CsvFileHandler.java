package com.excercise.paymentservice.adapter.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
class CsvFileHandler {

    @Value("${application.engine.csv.file-path}")
    private String filePath;
    @Value("${application.engine.csv.tmp-file-path}")
    private String tmpFilePath;



    public Optional<CSVRecord> findById(String paymentId) {

        try(BufferedReader reader = getReader(filePath)){

            Iterable<CSVRecord> records = getParser(reader);
            return StreamSupport.stream(records.spliterator(), false)
                    .filter(record -> paymentWithGivenId(record, paymentId))
                    .findFirst();

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }


    public List<CSVRecord> findAll() {

        try(BufferedReader reader = getReader(filePath)){

            Iterable<CSVRecord> records = getParser(reader);
            return StreamSupport.stream(records.spliterator(), false)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }


    public void save(Object[] recordValues) {

        try(BufferedWriter writer = getWriter(filePath)){

            CSVPrinter csvPrinter = getPrinter(writer);
            csvPrinter.printRecord(recordValues);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    public void update(String paymentId, Object[] recordValues) {

        try(BufferedReader reader = getReader(filePath);
            BufferedWriter writer = getWriter(tmpFilePath)) {

            Iterable<CSVRecord> records = getParser(reader);
            CSVPrinter csvPrinter = getPrinter(writer);
            for(CSVRecord record : records){

                if(paymentWithGivenId(record, paymentId)){
                    csvPrinter.printRecord(recordValues);
                }
                else {
                    csvPrinter.printRecord(toCsvValues(record));
                }
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        setFileWithUpdatedPaymentAsMain();
    }


    public void delete(String paymentId) {

        try(BufferedReader reader = getReader(filePath);
            BufferedWriter writer = getWriter(tmpFilePath)) {

            Iterable<CSVRecord> records = getParser(reader);
            CSVPrinter csvPrinter = getPrinter(writer);
            for(CSVRecord record : records){
                if(!paymentWithGivenId(record, paymentId)){
                    csvPrinter.printRecord(toCsvValues(record));
                }
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        setFileWithUpdatedPaymentAsMain();

    }


    private void setFileWithUpdatedPaymentAsMain() {
        try{
            Files.delete(Paths.get(filePath));
            Files.move(Paths.get(tmpFilePath), Paths.get(filePath), StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private boolean paymentWithGivenId(CSVRecord record, String id) {
        return id.equals(record.get(CsvPaymentFieldEnum.ID));
    }

    private CSVPrinter getPrinter(BufferedWriter writer) throws IOException {
        return new CSVPrinter(writer, CSVFormat.DEFAULT);
    }

    private BufferedReader getReader(String filePath) throws IOException {
        File fileToRead = new File(filePath);
        fileToRead.createNewFile();
        return Files.newBufferedReader(Paths.get(filePath));
    }

    private BufferedWriter getWriter(String filePath) throws IOException {
        return Files.newBufferedWriter(Paths.get(filePath),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND,
                StandardOpenOption.SYNC);
    }

    private CSVParser getParser(BufferedReader reader) throws IOException {
        return CSVFormat.DEFAULT
                .withHeader(CsvPaymentFieldEnum.class)
                .parse(reader);
    }

    private Object[] toCsvValues(CSVRecord record){
        return new String[]{
                record.get(CsvPaymentFieldEnum.ID),
                record.get(CsvPaymentFieldEnum.USER_ID),
                record.get(CsvPaymentFieldEnum.AMOUNT),
                record.get(CsvPaymentFieldEnum.CURRENCY),
                record.get(CsvPaymentFieldEnum.TARGET_BANK_ACCOUNT)
        };
    }


}