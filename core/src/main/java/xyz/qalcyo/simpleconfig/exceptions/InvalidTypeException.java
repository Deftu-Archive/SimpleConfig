package xyz.qalcyo.simpleconfig.exceptions;

public class InvalidTypeException extends RuntimeException {
    public InvalidTypeException(String message) {
        super(message);
    }
    public InvalidTypeException() {
        super();
    }

}