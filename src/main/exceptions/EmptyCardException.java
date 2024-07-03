package exceptions;

public class EmptyCardException extends Exception {
    public EmptyCardException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
