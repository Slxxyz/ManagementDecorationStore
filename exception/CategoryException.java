package exception;

public class CategoryException extends ExceptionType{
    public CategoryException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "category";
    }
}
