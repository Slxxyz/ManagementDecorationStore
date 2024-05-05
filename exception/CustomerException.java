package exception;

public class CustomerException extends ExceptionType{
    public CustomerException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "customer";
    }
}
