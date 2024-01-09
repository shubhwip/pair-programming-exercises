package me.shubhamjain.splitwise.splitstrategy;

public class SplitFactory {

    public static SplitStrategy getInstance(String splitType) {
        switch (splitType) {
            case "equal":
                return new EqualSplit();
            case "perc":
                return new PercentageSplit();
            case "abs":
                return new AbsoluteSplit();
            default:
                throw new UnsupportedOperationException("Split Type is not defined yet " + splitType);
        }
    }
}
