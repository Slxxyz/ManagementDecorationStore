package exception;

public class OneException extends RangeOperationException{
    @Override
    public String getDescription() {
        return "the";
    }
}
