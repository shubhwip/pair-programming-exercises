package com.pp.analyzer.cookie.filter;

public abstract class FilterDecorator extends Filter {
    Filter filter;

    public abstract String getDescription();
}
