package main;

public class ParserException extends RuntimeException {

    public ParserException(String message, int pos) {
        super(message + " at position: " + (pos - 1) + ".");
    }

    public ParserException(String message) {
        super(message);
    }
}
