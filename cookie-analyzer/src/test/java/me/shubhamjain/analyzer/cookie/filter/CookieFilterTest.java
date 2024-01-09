package me.shubhamjain.analyzer.cookie.filter;

import me.shubhamjain.analyzer.cookie.parser.CookieCSVParser;
import me.shubhamjain.analyzer.cookie.parser.IParser;
import me.shubhamjain.analyzer.cookie.processor.Processor;
import me.shubhamjain.analyzer.cookie.model.CookieData;
import me.shubhamjain.analyzer.cookie.model.CookieResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class CookieFilterTest {

    Processor processor;
    IParser cookieParser;

    @BeforeEach
    public void setUp() {
        cookieParser = new CookieCSVParser();
    }

    private String loadDataFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        return file.getAbsolutePath();
    }

    @Test
    void filterCookie_withDateFilterAndMostActiveCookieCountFilter_returnsResult() {
        // Arrange
        List<CookieData> cookiesData = (List<CookieData>) cookieParser.parse(loadDataFile("cookietestdata4.csv")).get();
        Filter filter = new DateFilter("2018-12-09");
        // Act
        filter = new MostActiveCookieFilter(filter);
        List<CookieResult> cookieResults = (List<CookieResult>) filter.applyFilter(cookiesData);
        // Assert
        Assertions.assertEquals(2, cookieResults.size());
    }

    @Test
    void filterCookie_withoutDateFilterAndMostActiveCookieCountFilter_returnsResult() {
        // Arrange
        List<CookieData> cookiesData = (List<CookieData>) cookieParser.parse(loadDataFile("cookietestdata4.csv")).get();
        Filter filter = new MostActiveCookieFilter(new NoFilter());
        // Act
        List<CookieResult> cookieResults = (List<CookieResult>) filter.applyFilter(cookiesData);
        // Assert
        Assertions.assertEquals(2, cookieResults.size());
    }

    @Test
    void filterCookie_withDateFilterAndMostActiveCookieCountFilter_WhenDateIsNotPresent_returnsResult() {
        // Arrange
        List<CookieData> cookiesData = (List<CookieData>) cookieParser.parse(loadDataFile("cookietestdata4.csv")).get();
        Filter filter = new MostActiveCookieFilter(new NoFilter());
        // Act
        List<CookieResult> cookieResults = (List<CookieResult>) filter.applyFilter(cookiesData);
        // Assert
        Assertions.assertEquals(2, cookieResults.size());
    }

    @Test
    void filterCookie_WhenAllCookiesOccursEqually_withDateFilterAndMostActiveCookieCountFilter_returnsAllTheResults() {
        // Arrange
        List<CookieData> cookiesData = (List<CookieData>) cookieParser.parse(loadDataFile("unique-cookie-data.csv")).get();
        Filter filter = new DateFilter("2018-12-10");
        // Act
        filter = new MostActiveCookieFilter(new NoFilter());
        List<CookieResult> cookieResults = (List<CookieResult>) filter.applyFilter(cookiesData);
        // Assert
        Assertions.assertEquals(5, cookieResults.size());
    }

    @Test
    void filterCookie_withDateFilterAndMostActiveCookieCountFilter_WhenDateIsNotPresentInTheData_returnsRuntimeException() {
        // Arrange
        List<CookieData> cookiesData = (List<CookieData>) cookieParser.parse(loadDataFile("unique-cookie-data.csv")).get();
        Filter filter = new DateFilter("2018-12-10");
        // Act
        filter = new MostActiveCookieFilter(filter);
        // Assert
        Filter finalFilter = filter;
        Assertions.assertThrows(RuntimeException.class, () -> {
            finalFilter.applyFilter(cookiesData);
        });
    }

    // Add cases for exceptions

    @AfterEach
    public void tearDown() {
        cookieParser = null;
        processor = null;
    }

}
