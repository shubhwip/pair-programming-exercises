package me.shubhamjain.blackjack.operation;

public class OperationFactory {

    public Operation getInstance(int operation) {
        if (OperationEnum.HIT.getOperationId() == operation)
            return new HitOperation();
        else if (OperationEnum.STAND.getOperationId() == operation)
            return new StayOperation();
        else if (OperationEnum.DOUBLE_DOWN.getOperationId() == operation)
            return new DoubleDownOperation();
        else if (OperationEnum.SURRENDER.getOperationId() == operation)
            return new SurrenderOperation();
        else
            throw new UnsupportedOperationException("Operation Not Found");
    }

}
