package my_gen.Signatures;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SignaturesLexicalAnalyzer {

    private final Matcher matcher;
    private Token curToken;

    public SignaturesLexicalAnalyzer (String expression) throws ParseException {
        Pattern pattern = Pattern.compile(",|\\]|\\*|eps|;|\\)|[_a-zA-Z]+|\\(|\\[|.");
        this.matcher = pattern.matcher(expression);
    }

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
}