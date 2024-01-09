package me.shubhamjain.analyzer.cookie.filter;

import me.shubhamjain.analyzer.cookie.model.CookieData;
import me.shubhamjain.analyzer.cookie.model.CookieResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class MostActiveCookieFilter extends FilterDecorator {
    public MostActiveCookieFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String getDescription() {
        return "Most Active Cookie Filter";
    }

    public List<CookieResult> applyFilter(List<CookieData> cookieDataList) {
        List<CookieData> l = (List<CookieData>) cookieDataList;
        if (filter != null) {
            l = (List<CookieData>) filter.applyFilter(l);
            if (l.isEmpty())
                throw new RuntimeException("No Data is available to find Most Active Cookie");
        }
        Map<String, Long> freqMap = l.stream().map(c -> c.getCookie())
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        Long maxFrequency = freqMap
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getValue();
        List<CookieResult> results = freqMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(maxFrequency))
                .map((k) -> new CookieResult(k.getKey()))
                .collect(Collectors.toList());
        for (CookieResult r : results) {
            log.debug("Most Active Cookie Filter Output is {}", r.getCookie());
        }
        return results;
    }
}
