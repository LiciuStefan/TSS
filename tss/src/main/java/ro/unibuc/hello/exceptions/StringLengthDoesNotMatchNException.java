package ro.unibuc.hello.exceptions;

public class StringLengthDoesNotMatchNException extends RuntimeException {

    public StringLengthDoesNotMatchNException() {
        super("String length does not match n");
    }
}
