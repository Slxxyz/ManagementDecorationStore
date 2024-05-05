package exception;

public class CreateException extends OperationException{
    @Override
    public String getDescription() {
        return "create";
    }
}
