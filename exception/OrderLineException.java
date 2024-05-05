package exception;

public class OrderLineException extends ExceptionType{
    public OrderLineException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "order line";
    }
}
