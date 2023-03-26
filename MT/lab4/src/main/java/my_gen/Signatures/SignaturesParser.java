package my_gen.Signatures;

public class SignaturesParser {

    private SignaturesLexicalAnalyzer lex;

    private A a() throws ParseException {
        A a_0 = new A("a");
        switch (lex.curToken().getType()) {
            case TYPE_OR_NAME -> {
                Tn tn_1 = tn();
                a_0.add(tn_1);
                APrime aPrime_2 = aPrime();
                a_0.add(aPrime_2);
			}
            case RBRACKET -> {
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (a_0.getChildren().size() == 0) {
            a_0.add(new Tree("EPS"));
            
        }
        return a_0;
    }

    private S s() throws ParseException {
        S s_0 = new S("s");
        switch (lex.curToken().getType()) {
            case TYPE_OR_NAME -> {
                Tnf tnf_1 = tnf();
                s_0.add(tnf_1);
                if (!lex.curToken().getType().name().equals("LBRACKET")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: LBRACKET");
                }
                String LBRACKET_2 = lex.curToken().getValue();
                s_0.add(new Tree("LBRACKET"));
                lex.nextToken();
                A a_3 = a();
                s_0.add(a_3);
                if (!lex.curToken().getType().name().equals("RBRACKET")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: RBRACKET");
                }
                String RBRACKET_4 = lex.curToken().getValue();
                s_0.add(new Tree("RBRACKET"));
                lex.nextToken();
                if (!lex.curToken().getType().name().equals("SEMICOLON")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: SEMICOLON");
                }
                String SEMICOLON_5 = lex.curToken().getValue();
                s_0.add(new Tree("SEMICOLON"));
                lex.nextToken();
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (s_0.getChildren().size() == 0) {
            s_0.add(new Tree("EPS"));
            
        }
        return s_0;
    }

    private Tn tn() throws ParseException {
        Tn tn_0 = new Tn("tn");
        switch (lex.curToken().getType()) {
            case TYPE_OR_NAME -> {
                Tnf tnf_1 = tnf();
                tn_0.add(tnf_1);
                M m_2 = m();
                tn_0.add(m_2);
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (tn_0.getChildren().size() == 0) {
            tn_0.add(new Tree("EPS"));
            
        }
        return tn_0;
    }

    private Tnf tnf() throws ParseException {
        Tnf tnf_0 = new Tnf("tnf");
        switch (lex.curToken().getType()) {
            case TYPE_OR_NAME -> {
                if (!lex.curToken().getType().name().equals("TYPE_OR_NAME")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: TYPE_OR_NAME");
                }
                String TYPE_OR_NAME_1 = lex.curToken().getValue();
                tnf_0.add(new Tree("TYPE_OR_NAME"));
                lex.nextToken();
                P p_2 = p();
                tnf_0.add(p_2);
                if (!lex.curToken().getType().name().equals("TYPE_OR_NAME")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: TYPE_OR_NAME");
                }
                String TYPE_OR_NAME_3 = lex.curToken().getValue();
                tnf_0.add(new Tree("TYPE_OR_NAME"));
                lex.nextToken();
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (tnf_0.getChildren().size() == 0) {
            tnf_0.add(new Tree("EPS"));
            
        }
        return tnf_0;
    }

    private M m() throws ParseException {
        M m_0 = new M("m");
        switch (lex.curToken().getType()) {
            case COMMA -> {
			}
            case SQUARE_OPEN -> {
                if (!lex.curToken().getType().name().equals("SQUARE_OPEN")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: SQUARE_OPEN");
                }
                String SQUARE_OPEN_1 = lex.curToken().getValue();
                m_0.add(new Tree("SQUARE_OPEN"));
                lex.nextToken();
                if (!lex.curToken().getType().name().equals("SQUARE_CLOSE")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: SQUARE_CLOSE");
                }
                String SQUARE_CLOSE_2 = lex.curToken().getValue();
                m_0.add(new Tree("SQUARE_CLOSE"));
                lex.nextToken();
			}
            case RBRACKET -> {
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (m_0.getChildren().size() == 0) {
            m_0.add(new Tree("EPS"));
            
        }
        return m_0;
    }

    private P p() throws ParseException {
        P p_0 = new P("p");
        switch (lex.curToken().getType()) {
            case POINTER -> {
                if (!lex.curToken().getType().name().equals("POINTER")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: POINTER");
                }
                String POINTER_1 = lex.curToken().getValue();
                p_0.add(new Tree("POINTER"));
                lex.nextToken();
                P p_2 = p();
                p_0.add(p_2);
			}
            case TYPE_OR_NAME -> {
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (p_0.getChildren().size() == 0) {
            p_0.add(new Tree("EPS"));
            
        }
        return p_0;
    }

    private APrime aPrime() throws ParseException {
        APrime aPrime_0 = new APrime("aPrime");
        switch (lex.curToken().getType()) {
            case COMMA -> {
                if (!lex.curToken().getType().name().equals("COMMA")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: COMMA");
                }
                String COMMA_1 = lex.curToken().getValue();
                aPrime_0.add(new Tree("COMMA"));
                lex.nextToken();
                Tn tn_2 = tn();
                aPrime_0.add(tn_2);
                APrime aPrime_3 = aPrime();
                aPrime_0.add(aPrime_3);
			}
            case RBRACKET -> {
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (aPrime_0.getChildren().size() == 0) {
            aPrime_0.add(new Tree("EPS"));
            
        }
        return aPrime_0;
    }

    public S parse(String expression) {
        this.lex = new SignaturesLexicalAnalyzer(expression);
        lex.nextToken();
        return s();
    }

    public class A extends Tree {
		
        public A(String node) {
            super(node);
        }
    }

    public class S extends Tree {
		
        public S(String node) {
            super(node);
        }
    }

    public class Tn extends Tree {
		
        public Tn(String node) {
            super(node);
        }
    }

    public class Tnf extends Tree {
		
        public Tnf(String node) {
            super(node);
        }
    }

    public class M extends Tree {
		
        public M(String node) {
            super(node);
        }
    }

    public class P extends Tree {
		
        public P(String node) {
            super(node);
        }
    }

    public class APrime extends Tree {
		
        public APrime(String node) {
            super(node);
        }
    }

}