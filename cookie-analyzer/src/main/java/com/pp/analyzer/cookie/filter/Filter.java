package com.pp.analyzer.cookie.filter;

import com.pp.analyzer.cookie.model.CookieData;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class Filter {
    private String description = "Unknown Filter";

    public abstract List<?> applyFilter(List<CookieData> cookieDataList);
}
