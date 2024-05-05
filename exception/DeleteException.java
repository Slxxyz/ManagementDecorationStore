package exception;

public class DeleteException extends OperationException{
    @Override
    public String getDescription() {
        return "delete";
    }
}
