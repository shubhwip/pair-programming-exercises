package com.pp.analyzer.cookie.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.pp.analyzer.cookie.model.CookieData;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CookieCSVParser implements IParser {
    public Optional<List<CookieData>> parse(String fileName) {
        log.info("Parsing CSV File {} contents", fileName);
        List<CookieData> cookieData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            reader.skip(1);
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                //CookieData c = new CookieData(lineInArray[0]);
                CookieData c = new CookieData();
                c.setCookie(lineInArray[0]);
                ZonedDateTime d = ZonedDateTime.parse(lineInArray[1]);
                c.setTimestamp(d);
                cookieData.add(c);
            }
        } catch (IOException | CsvValidationException e) {
            log.error("Exception occured in parsing csv file {} {}", fileName, e.getMessage());
        }
        for (CookieData c : cookieData) {
            log.debug("Cookie Details : cookie {} and timestamp {}\n", c.getCookie(), c.getTimestamp());
        }
        return Optional.of(cookieData);
    }
}
