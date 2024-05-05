package exception;

public class SupplierException extends ExceptionType{
    public SupplierException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "supplier";
    }
}
