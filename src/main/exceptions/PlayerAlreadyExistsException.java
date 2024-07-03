package exceptions;

public class PlayerAlreadyExistsException extends Exception {
    public PlayerAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
