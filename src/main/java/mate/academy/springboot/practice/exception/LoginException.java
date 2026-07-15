package mate.academy.springboot.practice.exception;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
