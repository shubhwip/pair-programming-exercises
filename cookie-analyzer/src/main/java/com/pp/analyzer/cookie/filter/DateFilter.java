package com.pp.analyzer.cookie.filter;

import com.pp.analyzer.cookie.model.CookieData;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
public class DateFilter extends Filter {

    final private String DATE_PATTERN = "yyyy-MM-dd";

    private String date;

    public DateFilter(String date) {
        this.date = date;
    }

    @Override
    public String getDescription() {
        return "Filter data by the date";
    }

    public List<CookieData> applyFilter(List<CookieData> cookieDataList) {
        //List<CookieData> l = cookieDataList;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH);
        LocalDate d1 = LocalDate.parse(date, formatter);
        List<CookieData> dateFilteredCookieData = cookieDataList.stream()
                .filter(c -> c.getTimestamp().toLocalDate().format(formatter).equals(formatter.format(d1)))
                .collect(Collectors.toList());
        for (CookieData c : dateFilteredCookieData) {
            log.debug("Date Filter Output is date {} and cookie {}", date, c.getCookie());
        }
        return dateFilteredCookieData;
    }
}
