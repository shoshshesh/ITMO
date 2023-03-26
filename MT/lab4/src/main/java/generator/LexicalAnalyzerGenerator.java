package generator;

import grammar.Grammar;
import grammar.term.Terminal;

public class LexicalAnalyzerGenerator extends AbstractGenerator {
    public LexicalAnalyzerGenerator(Grammar grammar) {
        super(grammar, grammar.getName() + "LexicalAnalyzer");
    }

    @Override
    protected String generateImports() {
        return """
                import java.util.regex.Pattern;
                import java.util.regex.Matcher;
                
                """;
    }

    @Override
    protected String generateFields() {
        return """
                    private final Matcher matcher;
                    private Token curToken;
                
                """;
    }

    private String makePattern() {
        StringBuilder pattern = new StringBuilder("\"");
        for (Terminal t : grammar.getTerminals()) {
            pattern.append(t.getRegExpr()).append("|");
        }
        pattern.deleteCharAt(pattern.length() - 1);
        pattern.append("|.\"");
        return pattern.toString();
    }

    @Override
    protected String generateConstructors() {
        return String.format("""
                    public %s (String expression) throws ParseException {
                        Pattern pattern = Pattern.compile(%s);
                        this.matcher = pattern.matcher(expression);
                    }
                    
                """, nameOfClass, makePattern());
    }

    @Override
    protected String generateMethods() {
        return """
                        public Token curToken() {
                            return curToken;
                        }
                        
                        public void nextToken() {
                            do {
                                if (!matcher.find()) {
                                    curToken = new Token(TokenTypes._END, "$");
                                    return;
                                }
                            } while (Character.isWhitespace(matcher.group().charAt(0)));
                            for (TokenTypes tokenType : TokenTypes.values()) {
                                String tokenStr = matcher.group();
                                if (tokenType.matches(tokenStr)) {
                                    curToken = new Token(tokenType, tokenStr);
                                    return;
                                }
                            }
                            throw new ParseException("Invalid character `" + matcher.group() + "`", matcher.end());
                        }
                    """;
    }
}
