package grammar.term;

public class Terminal extends Term {
    private final String regExpr;

    public Terminal(String name) {
        super(name);
        this.regExpr = "";
    }

    public Terminal(String name, String regExpr) {
        super(name);
        this.regExpr = regExpr;
    }

    public String getRegExpr() {
        return regExpr;
    }

    @Override
    public String toString() {
        return getName() /*+ " regExpr: " + regExpr*/;
    }
}
