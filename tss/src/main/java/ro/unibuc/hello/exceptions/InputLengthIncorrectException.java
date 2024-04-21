package ro.unibuc.hello.exceptions;

public class InputLengthIncorrectException extends RuntimeException {

    public InputLengthIncorrectException() {
        super("Input length is incorrect");
    }
}
