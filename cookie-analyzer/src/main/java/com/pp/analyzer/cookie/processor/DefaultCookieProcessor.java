package com.pp.analyzer.cookie.processor;

import com.pp.analyzer.cookie.filedownload.LoadFile;
import com.pp.analyzer.cookie.filter.DateFilter;
import com.pp.analyzer.cookie.filter.Filter;
import com.pp.analyzer.cookie.filter.MostActiveCookieFilter;
import com.pp.analyzer.cookie.model.CookieData;
import com.pp.analyzer.cookie.model.CookieResult;
import com.pp.analyzer.cookie.parser.IParser;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DefaultCookieProcessor implements Processor {

    private String date;
    private String fileLocation;
    private IParser cookieParser;
    private LoadFile loadFile;

    @Override
    public List<CookieResult> applyFilters() {
        String fileName = loadFile.loadFile(fileLocation);
        List<CookieData> cookiesData = (List<CookieData>) cookieParser
                .parse(fileName).orElse(new ArrayList<CookieData>());
        Filter filter = new DateFilter(date);
        filter = new MostActiveCookieFilter(filter);
        return (List<CookieResult>) filter.applyFilter(cookiesData);

    }
}
