package me.shubhamjain.analyzer.cookie.processor;

import me.shubhamjain.analyzer.cookie.filedownload.LoadFile;
import me.shubhamjain.analyzer.cookie.filter.DateFilter;
import me.shubhamjain.analyzer.cookie.filter.Filter;
import me.shubhamjain.analyzer.cookie.filter.MostActiveCookieFilter;
import me.shubhamjain.analyzer.cookie.model.CookieData;
import me.shubhamjain.analyzer.cookie.model.CookieResult;
import me.shubhamjain.analyzer.cookie.parser.IParser;
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
