package exception;

public class OrderCustomerException extends ExceptionType{
    public OrderCustomerException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "order customer";
    }
}
