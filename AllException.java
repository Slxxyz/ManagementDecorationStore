package exception;

public class AllException extends RangeOperationException{
    @Override
    public String getDescription() {
        return "all";
    }
}
