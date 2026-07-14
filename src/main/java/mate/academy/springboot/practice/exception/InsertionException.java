package mate.academy.springboot.practice.exception;

public class InsertionException extends RuntimeException {
    public InsertionException(String message) {
        super(message);
    }
}
