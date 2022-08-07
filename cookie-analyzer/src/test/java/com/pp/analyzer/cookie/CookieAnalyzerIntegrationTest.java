package com.pp.analyzer.cookie;

import com.pp.analyzer.cookie.filedownload.LoadFile;
import com.pp.analyzer.cookie.filedownload.LoadS3File;
import com.pp.analyzer.cookie.model.CookieResult;
import com.pp.analyzer.cookie.parser.CookieCSVParser;
import com.pp.analyzer.cookie.parser.IParser;
import com.pp.analyzer.cookie.processor.DefaultCookieProcessor;
import com.pp.analyzer.cookie.processor.Processor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CookieAnalyzerIntegrationTest {
    IParser cookieParser;
    LoadFile loadFile;
    Processor processor;

    @BeforeEach
    public void setUp() {
        cookieParser = new CookieCSVParser();
        loadFile = new LoadS3File();
    }

//
//    @Test
//    void filterCookie_withDefaultCookieProcessor_returnsResult() {
//        // Arrange
//        processor = new DefaultCookieProcessor("2018-12-09", "s3://bucket-data/data.csv", loadFile, cookieParser);
//        // Act
//        List<CookieResult> resultList = processor.applyFilters();
//        // Assert
//        Assertions.assertEquals(2, resultList.size());
//        Assertions.assertEquals("AtY0laUfhglK3lC7", resultList.get(0).getCookie());
//        Assertions.assertEquals("SAZuXPGUrfbcn5UA", resultList.get(1).getCookie());
//    }

    @Test
    void filterCookie_withDefaultCookieProcessor_returnsResult() {
        // Arrange
        processor = new DefaultCookieProcessor("2018-12-09", "src/main/resources/data.csv", cookieParser, loadFile);
        // Act
        List<CookieResult> resultList = processor.applyFilters();
        // Assert
        Assertions.assertEquals(2, resultList.size());
        Assertions.assertEquals("AtY0laUfhglK3lC7", resultList.get(0).getCookie());
        Assertions.assertEquals("SAZuXPGUrfbcn5UA", resultList.get(1).getCookie());
    }

    @AfterEach
    public void tearDown() {
        cookieParser = null;
        processor = null;
        loadFile = null;
    }


}
