package exception;

public class SupplierDetailException extends ExceptionType{
    public SupplierDetailException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "supplier detail";
    }
}
