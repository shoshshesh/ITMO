package my_gen.Calculator;

public enum TokenTypes {

	MINUS("-"),
	DIV("/"),
	ABS("abs"),
	MUL("\\*"),
	NUMBER("[0-9]+"),
	SIN("sin"),
	EPS("eps"),
	COS("cos"),
	SQRT("sqrt"),
	RBRACKET("\\)"),
	PLUS("\\+"),
	LBRACKET("\\("),
	_END("\\$");
	private final String regExpr;

    TokenTypes(String regExpr) {
        this.regExpr = regExpr;
    }

    public boolean matches(String expression) {
        return expression.matches(regExpr);
    }
}