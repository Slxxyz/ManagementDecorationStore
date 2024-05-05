package exception;

public abstract class ExceptionType extends Exception {
    private String messageError;
    private OperationException operation;
    private RangeOperationException rangeOperation;

    public ExceptionType (String messageError, RangeOperationException rangeOperation, OperationException operation) {
        this.setErrorMessage(messageError);
        this.setRangeOperation(rangeOperation);
        this.setOperation(operation);
    }

    public void setErrorMessage(String messageError) {
        this.messageError = messageError;
    }
    public String getMessageError() {
        return messageError;
    }
    public OperationException getOperation() {
        return operation;
    }
    public void setOperation(OperationException operation) {
        this.operation = operation;
    }
    public RangeOperationException getRangeOperation() {
        return rangeOperation;
    }
    public void setRangeOperation(RangeOperationException rangeOperation) {
        this.rangeOperation = rangeOperation;
    }
    protected abstract String getType();
    public String getDescription() {
        return String.format("Impossible de %s %s %s", operation.getDescription(), rangeOperation.getDescription(), getType());
    }

    public String getTitle() {
        return String.format("Error source %s", getType());
    }
}
