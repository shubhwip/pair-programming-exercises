package me.shubhamjain.analyzer.cookie.parser;

import me.shubhamjain.analyzer.cookie.model.CookieData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CookieCSVParserTest {

    IParser cookieParser;

    @BeforeEach
    public void setUp() throws Exception {
        cookieParser = new CookieCSVParser();
    }

    private String loadDataFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        return file.getAbsolutePath();
    }

    @ParameterizedTest
    @CsvSource({"cookietestdata1.csv,8", "cookietestdata2.csv,3", "cookietestdata3.csv,5"})
    void parseCookiesInCsvFile_WhenDataIsCorrectAndOfVaryingSizes(String csvFileName, int mExpectedResultCount) {
        // Act
        List<CookieData> cookiesData = (List<CookieData>) cookieParser.parse(loadDataFile(csvFileName)).orElse(new ArrayList<>());
        // Assert
        Assertions.assertEquals(mExpectedResultCount, cookiesData.size());
    }

    @Test
    void parseCookiesInCsvFile_WhenDataIsMalformed() {
        // Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            cookieParser.parse(loadDataFile("malformed-data.csv")).get();
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        cookieParser = null;
    }
}
