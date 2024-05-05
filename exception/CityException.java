package exception;

public class CityException extends ExceptionType{
    public CityException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "city";
    }
}
