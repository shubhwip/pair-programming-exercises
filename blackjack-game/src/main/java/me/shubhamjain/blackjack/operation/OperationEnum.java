package me.shubhamjain.blackjack.operation;

public enum OperationEnum {
    HIT(0),
    STAND(1),
    DOUBLE_DOWN(2),
    SURRENDER(3);

    private Integer operationId;

    private OperationEnum(final Integer operationId) {
        this.operationId = operationId;
    }

    public Integer getOperationId() {
        return operationId;
    }
}
