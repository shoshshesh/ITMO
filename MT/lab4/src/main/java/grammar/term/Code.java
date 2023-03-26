package grammar.term;

public class Code extends Term {
    private final String code;

    public Code(String code) {
        super("");
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
