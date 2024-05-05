package exception;

public class SaleReportException extends ExceptionType{
    public SaleReportException(String messageError, RangeOperationException rangeOperation, OperationException operation) {
        super(messageError, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "sale report";
    }
}
