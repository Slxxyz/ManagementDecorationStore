package exception;

public class ReadException extends OperationException{
    @Override
    public String getDescription() {
        return "read";
    }
}
