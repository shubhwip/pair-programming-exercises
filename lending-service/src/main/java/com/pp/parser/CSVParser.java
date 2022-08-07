package com.pp.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.pp.exception.InputFileParseException;
import com.pp.model.Lender;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CSVParser implements IParser<Lender> {
    @Override
    public List<Lender> getLenders(String fileName) throws InputFileParseException {
        Objects.requireNonNull(fileName, "filename should not be null");
        log.info("Parsing CSV File {} contents", fileName);
        List<Lender> lenders = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            reader.skip(1);
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                Lender l = new Lender();
                if (lineInArray[0].isEmpty()) {
                    log.error("Lender name is not present");
                    continue;
                }
                l.setName(lineInArray[0]);
                l.setRate(lineInArray[1].isEmpty() ? BigDecimal.ZERO : new BigDecimal(lineInArray[1]));
                l.setAvailable(lineInArray[2].isEmpty() ? BigDecimal.ZERO : new BigDecimal(lineInArray[2]));
                lenders.add(l);
            }
        } catch (IOException | CsvException e) {
            log.error("Exception occurred in parsing lenders csv file {} --- Message is {}", fileName, e.getStackTrace());
            throw new InputFileParseException("Exception occurred while parsing file" + fileName, e);
        }
        for (Lender l : lenders) {
            log.debug("Lender Details : lender {} \n", l);
        }
        return lenders;
    }
}
