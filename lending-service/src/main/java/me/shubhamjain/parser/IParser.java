package me.shubhamjain.parser;

import me.shubhamjain.exception.InputFileParseException;
import me.shubhamjain.model.Lender;

import java.util.List;

public interface IParser<L extends Lender> {
    List<L> getLenders(String filename) throws InputFileParseException;
}
