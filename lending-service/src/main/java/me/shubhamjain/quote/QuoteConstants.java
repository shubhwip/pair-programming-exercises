package me.shubhamjain.quote;

import me.shubhamjain.utility.PropertyLoader;

public class QuoteConstants {


    public final static int DEFAULT_LOAN_PERIOD = PropertyLoader.getIntValue("loan.period");
    public final static int DEFAULT_NUMBER_SCALE = PropertyLoader.getIntValue("default.numbers.scale");
    public final static String DEFAULT_LENDERS_FILE_PATH = String.valueOf(PropertyLoader.getStringValue("default.lenders.csv.file"));
    public final static String CURRENCY = PropertyLoader.getStringValue("currency.code");
    public final static String LOAN_AMORTIZATION_PERIOD = PropertyLoader.getStringValue("loan.amortization.period");
    public final static String LOAN_COMPOUNDING_TYPE = PropertyLoader.getStringValue("loan.compounding.type");
    public final static String LOAN_LOWER_REQUEST_LIMIT = PropertyLoader.getStringValue("loan.lower.request.limits");
    public final static String LOAN_UPPER_REQUEST_LIMIT = PropertyLoader.getStringValue("loan.upper.request.limits");
    public final static String LOAN_REQUEST_MULTIPLES = PropertyLoader.getStringValue("loan.request.multiples");
}
