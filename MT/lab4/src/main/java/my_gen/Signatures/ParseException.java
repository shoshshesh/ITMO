package my_gen.Signatures;

public class ParseException extends RuntimeException {

    public ParseException(String message, int pos) {
        super(message + " at position: " + (pos - 1) + ".");
    }

    public ParseException(String message) {
        super(message);
    }

}