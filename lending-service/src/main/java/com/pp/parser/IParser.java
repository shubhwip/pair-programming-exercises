package com.pp.parser;

import com.pp.exception.InputFileParseException;
import com.pp.model.Lender;

import java.util.List;

public interface IParser<L extends Lender> {
    List<L> getLenders(String filename) throws InputFileParseException;
}
