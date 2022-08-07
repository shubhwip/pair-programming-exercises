package com.pp.analyzer.cookie.parser;

import java.util.List;
import java.util.Optional;

public interface IParser<T> {
    <T> Optional<List<T>> parse(String fileName);
}
