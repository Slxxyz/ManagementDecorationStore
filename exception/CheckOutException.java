package exception;

public class CheckOutException extends ExceptionType{
    public CheckOutException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "check out";
    }
}
