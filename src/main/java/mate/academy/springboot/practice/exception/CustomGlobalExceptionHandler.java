package mate.academy.springboot.practice.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors().stream()
                .map(this::getErrorMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred. " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>("Entity not found exception occurred. "
                + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(RegistrationException ex) {
        return new ResponseEntity<>("An error occurred during registration. " + ex.getMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>("An authentication error occurred. " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsertionException.class)
    public ResponseEntity<String> handleInsertionException(InsertionException ex) {
        return new ResponseEntity<>("An error during insertion. " + ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleLoginException(LoginException ex) {
        return new ResponseEntity<>("An error during login. " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OrderProcessingException.class)
    public ResponseEntity<String> handleOrderProcessingException(OrderProcessingException ex) {
        return new ResponseEntity<>("An error during processing order. " + ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    private String getErrorMessage(ObjectError e) {
        if (e instanceof FieldError fieldError) {
            String field = fieldError.getField();
            String message = e.getDefaultMessage();
            return field + " field " + message;
        }

        return e.getDefaultMessage();
    }
}
