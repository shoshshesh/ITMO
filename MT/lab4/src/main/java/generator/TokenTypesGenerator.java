package generator;

import grammar.Grammar;
import grammar.term.Terminal;

public class TokenTypesGenerator extends AbstractGenerator {


    public TokenTypesGenerator(Grammar grammar) {
        super(grammar, "TokenTypes");
    }

    @Override
    protected String generateImports() {
        return "";
    }

    @Override
    protected String generateStartOfClass() {
        return "public enum TokenTypes {\n\n";
    }

    @Override
    protected String generateFields() {
        StringBuilder tokens = new StringBuilder();
        for (Terminal t : grammar.getTerminals()) {
            tokens.append("\t").append(t.getName()).append("(\"").append(t.getRegExpr()).append("\"),\n");
        }
        tokens.append("\t_END(\"\\\\$\");\n\t");
        tokens.append("private final String regExpr;\n\n");
        return tokens.toString();
    }

    @Override
    protected String generateConstructors() {
        return """
                    TokenTypes(String regExpr) {
                        this.regExpr = regExpr;
                    }
                
                """;
    }

    @Override
    protected String generateMethods() {
        return """
                        public boolean matches(String expression) {
                            return expression.matches(regExpr);
                        }
                    """;
    }
}
