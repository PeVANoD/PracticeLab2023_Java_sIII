package exceptions;

public class InconsistentFunctionsException extends RuntimeException {
    public InconsistentFunctionsException() {
        super("Inconsistent functions");
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}