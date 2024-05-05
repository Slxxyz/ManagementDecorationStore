package exception;

public class ProductException extends ExceptionType{
    public ProductException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "product";
    }
}
