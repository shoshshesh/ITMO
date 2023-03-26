package generator;

import grammar.Grammar;

public class TokenGenerator extends AbstractGenerator {

    public TokenGenerator(Grammar grammar) {
        super(grammar, "Token");
    }

    @Override
    protected String generateImports() {
        return "";
    }

    @Override
    protected String generateFields() {
        return """
                    private final TokenTypes type;
                    private final String value;

                """;
    }

    @Override
    protected String generateConstructors() {
        return """
                        public Token(TokenTypes type, String value) {
                            this.type = type;
                            this.value = value;
                        }

                    """;
    }

    @Override
    protected String generateMethods() {
        return """
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
                    """;
    }
}
