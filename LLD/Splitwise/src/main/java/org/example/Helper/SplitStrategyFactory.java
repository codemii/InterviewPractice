package org.example.Helper;

import org.example.model.SplitType;

public class SplitStrategyFactory {
    public SplitStrategy createSplitStrategy(SplitType splitType) {
        switch (splitType) {
            case EQUAL:
                return new EqualSplitStrategy();
            case EXACT:
                return new ExactSplitStrategy();
            case PERCENTAGE:
                return new PercentageSplitStrategy();
            default:
                throw new IllegalArgumentException("Not a valid strategy type");
        }
    }
}
