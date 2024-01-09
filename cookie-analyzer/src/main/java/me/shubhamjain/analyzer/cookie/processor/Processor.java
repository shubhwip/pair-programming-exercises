package me.shubhamjain.analyzer.cookie.processor;

import me.shubhamjain.analyzer.cookie.model.CookieResult;

import java.util.List;

public interface Processor {
    List<CookieResult> applyFilters();
}
