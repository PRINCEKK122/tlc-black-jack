package exceptions;

public class PlayerAlreadyExists extends Exception {
    public PlayerAlreadyExists(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
