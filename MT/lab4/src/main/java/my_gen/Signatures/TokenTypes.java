package my_gen.Signatures;

public enum TokenTypes {

	COMMA(","),
	SQUARE_CLOSE("\\]"),
	POINTER("\\*"),
	EPS("eps"),
	SEMICOLON(";"),
	RBRACKET("\\)"),
	TYPE_OR_NAME("[_a-zA-Z]+"),
	LBRACKET("\\("),
	SQUARE_OPEN("\\["),
	_END("\\$");
	private final String regExpr;

    TokenTypes(String regExpr) {
        this.regExpr = regExpr;
    }

    public boolean matches(String expression) {
        return expression.matches(regExpr);
    }
}