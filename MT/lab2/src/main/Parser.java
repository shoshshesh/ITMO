package main;

import java.io.InputStream;

public class Parser {
    private LexicalAnalyzer lex;

    private Tree S() throws ParserException {
        final Tree cur = new Tree("S");
        if (lex.getCurToken() == Token.TYPE_OR_NAME) {
            cur.add(TNF());
            if (lex.getCurToken() != Token.LBRACKET) {
                throw new ParserException(
                        "Found: " + lex.getCurToken() + ". Expected: '('.");
            }
            cur.add(new Tree("("));
            lex.nextToken();
            final Tree args = A();
            if (args.getChildren().size() != 0) {
                cur.add(args);
            }
            if (lex.getCurToken() != Token.RBRACKET) {
                throw new ParserException(
                        "Found: " + lex.getCurToken() + ". Expected: ')'.");
            }
            cur.add(new Tree(")"));
            lex.nextToken();
            if (lex.getCurToken() != Token.SEMICOLON) {
                throw new ParserException(
                        "Found: " + lex.getCurToken() + ". Expected: ';'.");
            }
            cur.add(new Tree(";"));
            lex.nextToken();
            if (lex.getCurToken() != Token.END) {
                throw new ParserException("End of heading expected but found: " + lex.getCurToken());
            }
        } else {
            throw new ParserException(
                    "Found: " + lex.getCurToken() + ". Expected: type and name.");
        }
        return cur;
    }

    private Tree TNF() throws ParserException {
        final Tree cur = new Tree("TNF");
        if (lex.getCurToken() == Token.TYPE_OR_NAME) {
            cur.add(new Tree(Token.TYPE_OR_NAME.toString()));
            lex.nextToken();
            final Tree pointers = P();
            if (pointers.getChildren().size() != 0) {
                cur.add(pointers);
            }
            if (lex.getCurToken() != Token.TYPE_OR_NAME) {
                throw new ParserException(
                        "Found: " + lex.getCurToken() + ". Expected: name.");
            }
            cur.add(new Tree(Token.TYPE_OR_NAME.toString()));
            lex.nextToken();
        } else {
            throw new ParserException(
                    "Found: " + lex.getCurToken() + ". Expected: type.");
        }
        return cur;
    }
    private Tree A() throws ParserException {
        final Tree cur = new Tree("A");
        switch (lex.getCurToken()) {
            case TYPE_OR_NAME -> {
                cur.add(TN());
                final Tree args = APrime();
                if (args.getChildren().size() != 0) {
                    cur.add(args);
                }
            }
            case RBRACKET -> {}
            default -> throw new ParserException(
                    "Found: " + lex.getCurToken() + ". Expected: type or ')'.");
        }
        return cur;
    }

    private Tree APrime() {
        final Tree cur = new Tree("A'");
        switch (lex.getCurToken()) {
            case COMA -> {
                cur.add(new Tree(","));
                lex.nextToken();
                cur.add(TN());
                final Tree args = APrime();
                if (args.getChildren().size() != 0) {
                    cur.add(args);
                }
            }
            case RBRACKET -> {}
            default -> throw new ParserException(
                    "Found: " + lex.getCurToken() + ". Expected: ',' or ')'.");
        }
        return cur;
    }

    private Tree TN() {
        final Tree cur = new Tree("TN");
        if (lex.getCurToken() == Token.TYPE_OR_NAME) {
            cur.add(TNF());
            final Tree array = M();
            if (array.getChildren().size() != 0) {
                cur.add(array);
            }
        } else {
            throw new ParserException(
                    "Found: " + lex.getCurToken() + ". Expected: type.");
        }
        return cur;
    }

    private Tree M() throws ParserException {
        final Tree cur = new Tree("M");
        switch (lex.getCurToken()) {
            case L_SQUARE -> {
                cur.add(new Tree(Token.L_SQUARE.toString()));
                lex.nextToken();
                if (lex.getCurToken() != Token.R_SQUARE) {
                    throw new ParserException(
                            "Found: " + lex.getCurToken() + ". Expected: ']'.");
                }
                cur.add(new Tree(Token.R_SQUARE.toString()));
                lex.nextToken();
            }
            case RBRACKET, COMA -> {}
            default -> throw new ParserException(
                    "Found: " + lex.getCurToken() + ". Expected: '['.");
        }
        return cur;
    }

    private Tree P() {
        final Tree cur = new Tree("P");
        switch (lex.getCurToken()) {
            case POINTER -> {
                cur.add(new Tree(Token.POINTER.toString()));
                lex.nextToken();
                final Tree pointers = P();
                if (pointers.getChildren().size() != 0) {
                    cur.add(pointers);
                }
            }
            case TYPE_OR_NAME -> {}
            default -> throw new ParserException(
                    "Found: " + lex.getCurToken() + ". Expected: '*' or name.");
        }
        return cur;
    }

    public Tree parse(InputStream io) throws ParserException {
        this.lex = new LexicalAnalyzer(io);
        lex.nextToken();
        return S();
    }
}


//package main;
//
//import java.io.InputStream;
//
//public class Parser {
//    private LexicalAnalyzer lex;
//
//    private Tree S() throws ParserException {
//        final Tree cur = new Tree("S");
//        if (lex.getCurToken() == Token.TYPE_AND_NAME) {
//            cur.add(TN());
//            if (lex.getCurToken() != Token.LBRACKET) {
//                throw new ParserException(
//                        "Found: " + lex.getCurToken() + ". Expected: '(' right before "
//                                + lex.getCurPos() + " position.");
//            }
//            cur.add(new Tree("("));
//            lex.nextToken();
//            final Tree args = A();
//            if (args.getChildren().size() != 0) {
//                cur.add(args);
//            }
//            if (lex.getCurToken() != Token.RBRACKET) {
//                throw new ParserException(
//                        "Found: " + lex.getCurToken() + ". Expected: ')' right before "
//                                + lex.getCurPos() + " position.");
//            }
//            cur.add(new Tree(")"));
//            lex.nextToken();
//            if (lex.getCurToken() != Token.SEMICOLON) {
//                throw new ParserException(
//                        "Found: " + lex.getCurToken() + ". Expected: ';' right before "
//                                + lex.getCurPos() + " position.");
//            }
//            cur.add(new Tree(";"));
//            lex.nextToken();
//            if (lex.getCurToken() != Token.END) {
//                throw new ParserException("End of heading expected but found: " + lex.getCurToken());
//            }
//        } else {
//            throw new ParserException(
//                    "Found: " + lex.getCurToken() + ". Expected: Type and name right before "
//                    + lex.getCurPos() + " position.");
//        }
//        return cur;
//    }
//
//    private Tree A() throws ParserException {
//        final Tree cur = new Tree("A");
//        switch (lex.getCurToken()) {
//            case TYPE_AND_NAME -> {
//                cur.add(TN());
//                final Tree args = APrime();
//                if (args.getChildren().size() != 0) {
//                    cur.add(args);
//                }
//            }
//            case RBRACKET -> {}
//            default -> throw new ParserException(
//                    "Found: " + lex.getCurToken() + ". Expected: Type and name or ')' right before "
//                    + lex.getCurPos() + " position.");
//        }
//        return cur;
//    }
//
//    private Tree APrime() {
//        final Tree cur = new Tree("A'");
//        switch (lex.getCurToken()) {
//            case COMA -> {
//                cur.add(new Tree(","));
//                lex.nextToken();
//                cur.add(TN());
//                final Tree args = APrime();
//                if (args.getChildren().size() != 0) {
//                    cur.add(args);
//                }
//            }
//            case RBRACKET -> {}
//            default -> throw new ParserException(
//                    "Found: " + lex.getCurToken() + ". Expected: ',' or ')' right before "
//                            + lex.getCurPos() + " position.");
//        }
//        return cur;
//    }
//
//    private Tree TN() {
//        final Tree cur = new Tree("TN");
//        if (lex.getCurToken() == Token.TYPE_AND_NAME) {
//            cur.add(new Tree(Token.TYPE_AND_NAME.toString()));
//            lex.nextToken();
//        } else {
//            throw new ParserException(
//                    "Found: " + lex.getCurToken() + ". Expected: type and name right before "
//                            + lex.getCurPos() + " position.");
//        }
//        return cur;
//    }
//
//    public Tree parse(InputStream io) throws ParserException {
//        this.lex = new LexicalAnalyzer(io);
//        lex.nextToken();
//        return S();
//    }
//}
