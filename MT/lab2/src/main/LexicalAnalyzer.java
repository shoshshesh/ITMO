package main;

import java.io.IOException;
import java.io.InputStream;

public class LexicalAnalyzer {
    private final InputStream is;
    private int curPos;
    private int curChar;
    private Token curToken;
    public LexicalAnalyzer(InputStream is) {
        this.is = is;
        curPos = 0;
        nextChar();
    }

    public Token getCurToken() {
        return this.curToken;
    }

    public int getCurPos() {
        return this.curPos;
    }

    public void nextToken() {
        skipWhitespaces();
        switch (curChar) {
            case ' ':
                skipWhitespaces();
                nextToken();
                break;
            case '(':
                nextChar();
                curToken = Token.LBRACKET;
                break;
            case ')':
                nextChar();
                curToken = Token.RBRACKET;
                break;
            case ';':
                nextChar();
                curToken = Token.SEMICOLON;
                break;
            case ',':
                nextChar();
                curToken = Token.COMA;
                break;
            case '*':
                nextChar();
                curToken = Token.POINTER;
                break;
            case '[':
                nextChar();
                curToken = Token.L_SQUARE;
                break;
            case ']':
                nextChar();
                curToken = Token.R_SQUARE;
                break;
            case -1:
                curToken = Token.END;
                break;
            default:
                if (isStartWordSymbol(curChar)) {
                    do {
                        nextChar();
                    } while (isWordSymbol(curChar));
                    curToken = Token.TYPE_OR_NAME;
                    break;
                } else {
                    throw new ParserException("Illegal character '" + (char) curChar + "'", curPos);
                }
        }
    }

    private boolean isStartWordSymbol(int c) {
        return isBetween(c, 'A', 'Z') || isBetween(c, 'a', 'z') || c == '_' ;
    }

    private void nextChar() throws ParserException {
        try {
            curChar = is.read();
            curPos++;
        } catch (IOException e) {
            throw new ParserException(e.getMessage(), curPos);
        }
    }

    private boolean isBlank(int c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    private boolean isWordSymbol(int c) {
        return isStartWordSymbol(c) || isDigit(c) ;
    }

    private boolean isDigit(int c) {
        return isBetween(c, '0', '9');
    }

    private void skipWhitespaces() {
        while (isBlank(curChar)) {
            nextChar();
        }
    }

    private boolean isBetween(int c, int left, int right) {
        return left <= c && c <= right;
    }
}


















//package main;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashSet;
//import java.util.Set;
//
//public class LexicalAnalyzer {
//    private final InputStream is;
//    private int curPos;
//    private int curChar;
//    private Token curToken;
//
//    private final Set<String> TYPES = Set.of("int", "long", "float", "double", "char", "void");
//    private final Set<String> usedNames = new HashSet<>();
//    private boolean isArg = false;
//
//    public LexicalAnalyzer(InputStream is) {
//        this.is = is;
//        curPos = 0;
//        nextChar();
//    }
//
//    public Token getCurToken() {
//        return this.curToken;
//    }
//
//    public int getCurPos() {
//        return this.curPos;
//    }
//
//    public void nextToken() {
//        skipWhitespaces();
//        switch (curChar) {
//            case ' ':
//                skipWhitespaces();
//                nextToken();
//                break;
//            case '(':
//                nextChar();
//                curToken = Token.LBRACKET;
//                break;
//            case ')':
//                nextChar();
//                curToken = Token.RBRACKET;
//                break;
//            case ';':
//                nextChar();
//                curToken = Token.SEMICOLON;
//                break;
//            case ',':
//                nextChar();
//                curToken = Token.COMA;
//                break;
//            case -1:
//                curToken = Token.END;
//                break;
//            default:
//                if (isWordSymbol(curChar)) {
//                    parseTypeAndName();
//                    curToken = Token.TYPE_AND_NAME;
//                    break;
//                } else {
//                    throw new ParserException("Illegal character '" + (char) curChar + "'", curPos);
//                }
//        }
//    }
//
//    private void nextChar() throws ParserException {
//        try {
//            curChar = is.read();
//            curPos++;
//        } catch (IOException e) {
//            throw new ParserException(e.getMessage(), curPos);
//        }
//    }
//
//    private boolean isBlank(int c) {
//        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
//    }
//
//    private boolean isWordSymbol(int c) {
//        return isBetween(c, 'A', 'Z') || isBetween(c, 'a', 'z') || isDigit(c) || c == '_' ;
//    }
//
//    private boolean isDigit(int c) {
//        return isBetween(c, '0', '9');
//    }
//
//    private void skipWhitespaces() {
//        while (isBlank(curChar)) {
//            nextChar();
//        }
//    }
//
//    private void parseTypeAndName() {
//        if (parseType()) {
//            parsePointers();
//        }
//        parseName();
//        isArg = true;
//    }
//
//    private boolean parseType() {
//        String result = parseWord();
//        if (!TYPES.contains(result)) {
//            throw new ParserException("Unknown type " + result, curPos - result.length());
//        }
//        if (isArg && result.equals("void")) { //В аргументах void может быть только с *.
//            int pos = curPos;
//            if (parsePointers() == 0) {
//                throw new ParserException("Void argument cannot be used without '*'. Expected '*'", pos);
//            }
//            return false;
//        }
//        return true;
//    }
//
//    private void parseName() {
//        String result = parseWord();
//        if (result.length() == 0) {
//            throw new ParserException("Name expected", curPos);
//        }
//        if (TYPES.contains(result)) {
//            throw new ParserException("Illegal name (name cannot be the same as type) '" + result + "'",
//                    curPos - result.length());
//        }
//        if (!isArg) { // Имя функции и переменной может совпадать, поэтому не добавляем имя функции в usedNames.
//            return;
//        }
//        if (!usedNames.add(result)) {
//            throw new ParserException("Second entry of the name \"" + result + "\"", curPos - result.length());
//        }
//    }
//
//    private int parsePointers() {
//        int counter = 0;
//        while (curChar == '*' || isBlank(curChar)) {
//            if (curChar == '*') {
//                counter++;
//            }
//            nextChar();
//        }
//        return counter;
//    }
//
//    private String parseWord() {
//        if (isDigit(curChar)) {
//            throw new ParserException("Type and name cannot start with digit. Found: '"
//                    + (char) curChar + "'", curPos);
//        }
//        StringBuilder word = new StringBuilder();
//        while (isWordSymbol(curChar)) {
//            word.append((char) curChar);
//            nextChar();
//        }
//        return word.toString();
//    }
//
//    private boolean isBetween(int c, int left, int right) {
//        return left <= c && c <= right;
//    }
//}
