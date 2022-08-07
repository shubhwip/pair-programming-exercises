package com.pp.analyzer.cookie.processor;

import com.pp.analyzer.cookie.model.CookieResult;

import java.util.List;

public interface Processor {
    List<CookieResult> applyFilters();
}
