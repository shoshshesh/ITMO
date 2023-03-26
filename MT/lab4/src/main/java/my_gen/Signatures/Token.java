package my_gen.Signatures;

public class Token {

    private final TokenTypes type;
    private final String value;

    public Token(TokenTypes type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenTypes getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type.name() + "(" + value + ")";
    }
}