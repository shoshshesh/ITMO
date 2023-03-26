package my_gen.Calculator;

public class CalculatorParser {

    private CalculatorLexicalAnalyzer lex;

    private T t() throws ParseException {
        T t_0 = new T("t");
        switch (lex.curToken().getType()) {
            case ABS -> {
                F f_1 = f();
                t_0.add(f_1);
                TPrime tPrime_2 = tPrime(f_1.val);
                t_0.add(tPrime_2);
				{
        t_0.val = tPrime_2.val;
      }
			}
            case NUMBER -> {
                F f_1 = f();
                t_0.add(f_1);
                TPrime tPrime_2 = tPrime(f_1.val);
                t_0.add(tPrime_2);
				{
        t_0.val = tPrime_2.val;
      }
			}
            case COS -> {
                F f_1 = f();
                t_0.add(f_1);
                TPrime tPrime_2 = tPrime(f_1.val);
                t_0.add(tPrime_2);
				{
        t_0.val = tPrime_2.val;
      }
			}
            case SQRT -> {
                F f_1 = f();
                t_0.add(f_1);
                TPrime tPrime_2 = tPrime(f_1.val);
                t_0.add(tPrime_2);
				{
        t_0.val = tPrime_2.val;
      }
			}
            case SIN -> {
                F f_1 = f();
                t_0.add(f_1);
                TPrime tPrime_2 = tPrime(f_1.val);
                t_0.add(tPrime_2);
				{
        t_0.val = tPrime_2.val;
      }
			}
            case LBRACKET -> {
                F f_1 = f();
                t_0.add(f_1);
                TPrime tPrime_2 = tPrime(f_1.val);
                t_0.add(tPrime_2);
				{
        t_0.val = tPrime_2.val;
      }
			}
            case MINUS -> {
                F f_1 = f();
                t_0.add(f_1);
                TPrime tPrime_2 = tPrime(f_1.val);
                t_0.add(tPrime_2);
				{
        t_0.val = tPrime_2.val;
      }
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (t_0.getChildren().size() == 0) {
            t_0.add(new Tree("EPS"));
            
        }
        return t_0;
    }

    private E e() throws ParseException {
        E e_0 = new E("e");
        switch (lex.curToken().getType()) {
            case ABS -> {
                T t_1 = t();
                e_0.add(t_1);
                EPrime ePrime_2 = ePrime(t_1.val);
                e_0.add(ePrime_2);
				{
        e_0.val = ePrime_2.val;
      }
			}
            case NUMBER -> {
                T t_1 = t();
                e_0.add(t_1);
                EPrime ePrime_2 = ePrime(t_1.val);
                e_0.add(ePrime_2);
				{
        e_0.val = ePrime_2.val;
      }
			}
            case COS -> {
                T t_1 = t();
                e_0.add(t_1);
                EPrime ePrime_2 = ePrime(t_1.val);
                e_0.add(ePrime_2);
				{
        e_0.val = ePrime_2.val;
      }
			}
            case SQRT -> {
                T t_1 = t();
                e_0.add(t_1);
                EPrime ePrime_2 = ePrime(t_1.val);
                e_0.add(ePrime_2);
				{
        e_0.val = ePrime_2.val;
      }
			}
            case SIN -> {
                T t_1 = t();
                e_0.add(t_1);
                EPrime ePrime_2 = ePrime(t_1.val);
                e_0.add(ePrime_2);
				{
        e_0.val = ePrime_2.val;
      }
			}
            case LBRACKET -> {
                T t_1 = t();
                e_0.add(t_1);
                EPrime ePrime_2 = ePrime(t_1.val);
                e_0.add(ePrime_2);
				{
        e_0.val = ePrime_2.val;
      }
			}
            case MINUS -> {
                T t_1 = t();
                e_0.add(t_1);
                EPrime ePrime_2 = ePrime(t_1.val);
                e_0.add(ePrime_2);
				{
        e_0.val = ePrime_2.val;
      }
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (e_0.getChildren().size() == 0) {
            e_0.add(new Tree("EPS"));
            
        }
        return e_0;
    }

    private TPrime tPrime(int acc) throws ParseException {
        TPrime tPrime_0 = new TPrime("tPrime");
        switch (lex.curToken().getType()) {
            case DIV -> {
                if (!lex.curToken().getType().name().equals("DIV")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: DIV");
                }
                String DIV_1 = lex.curToken().getValue();
                tPrime_0.add(new Tree("DIV"));
                lex.nextToken();
                F f_2 = f();
                tPrime_0.add(f_2);
				{
        tPrime_0.val = acc / f_2.val;
      }
                TPrime tPrime_3 = tPrime(tPrime_0.val);
                tPrime_0.add(tPrime_3);
				{
        tPrime_0.val = tPrime_3.val;
      }
			}
            case _END -> {
			}
            case MUL -> {
                if (!lex.curToken().getType().name().equals("MUL")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: MUL");
                }
                String MUL_1 = lex.curToken().getValue();
                tPrime_0.add(new Tree("MUL"));
                lex.nextToken();
                F f_2 = f();
                tPrime_0.add(f_2);
				{
        tPrime_0.val = acc * f_2.val;
      }
                TPrime tPrime_3 = tPrime(tPrime_0.val);
                tPrime_0.add(tPrime_3);
				{
        tPrime_0.val = tPrime_3.val;
      }
			}
            case RBRACKET -> {
			}
            case MINUS -> {
			}
            case PLUS -> {
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (tPrime_0.getChildren().size() == 0) {
            tPrime_0.add(new Tree("EPS"));
            {
        System.out.println("tPrime is empty, cur acc: " + acc);
        tPrime_0.val = acc;
      }
        }
        return tPrime_0;
    }

    private F f() throws ParseException {
        F f_0 = new F("f");
        switch (lex.curToken().getType()) {
            case ABS -> {
                if (!lex.curToken().getType().name().equals("ABS")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: ABS");
                }
                String ABS_1 = lex.curToken().getValue();
                f_0.add(new Tree("ABS"));
                lex.nextToken();
                F f_2 = f();
                f_0.add(f_2);
				{
        f_0.val = Math.abs(f_2.val);
    }
			}
            case NUMBER -> {
                if (!lex.curToken().getType().name().equals("NUMBER")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: NUMBER");
                }
                String NUMBER_1 = lex.curToken().getValue();
                f_0.add(new Tree("NUMBER"));
                lex.nextToken();
				{
        f_0.val = Integer.parseInt(NUMBER_1);
      }
			}
            case COS -> {
                if (!lex.curToken().getType().name().equals("COS")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: COS");
                }
                String COS_1 = lex.curToken().getValue();
                f_0.add(new Tree("COS"));
                lex.nextToken();
                F f_2 = f();
                f_0.add(f_2);
				{
        f_0.val = (int) Math.cos(f_2.val);
    }
			}
            case SQRT -> {
                if (!lex.curToken().getType().name().equals("SQRT")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: SQRT");
                }
                String SQRT_1 = lex.curToken().getValue();
                f_0.add(new Tree("SQRT"));
                lex.nextToken();
                F f_2 = f();
                f_0.add(f_2);
				{
        f_0.val = (int) Math.sqrt(f_2.val);
    }
			}
            case SIN -> {
                if (!lex.curToken().getType().name().equals("SIN")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: SIN");
                }
                String SIN_1 = lex.curToken().getValue();
                f_0.add(new Tree("SIN"));
                lex.nextToken();
                F f_2 = f();
                f_0.add(f_2);
				{
        f_0.val = (int) Math.sin(f_2.val);
      }
			}
            case LBRACKET -> {
                if (!lex.curToken().getType().name().equals("LBRACKET")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: LBRACKET");
                }
                String LBRACKET_1 = lex.curToken().getValue();
                f_0.add(new Tree("LBRACKET"));
                lex.nextToken();
                E e_2 = e();
                f_0.add(e_2);
                if (!lex.curToken().getType().name().equals("RBRACKET")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: RBRACKET");
                }
                String RBRACKET_3 = lex.curToken().getValue();
                f_0.add(new Tree("RBRACKET"));
                lex.nextToken();
				{
        f_0.val = e_2.val;
      }
			}
            case MINUS -> {
                if (!lex.curToken().getType().name().equals("MINUS")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: MINUS");
                }
                String MINUS_1 = lex.curToken().getValue();
                f_0.add(new Tree("MINUS"));
                lex.nextToken();
                F f_2 = f();
                f_0.add(f_2);
				{
        f_0.val = -f_2.val;
      }
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (f_0.getChildren().size() == 0) {
            f_0.add(new Tree("EPS"));
            
        }
        return f_0;
    }

    private EPrime ePrime(int acc) throws ParseException {
        EPrime ePrime_0 = new EPrime("ePrime");
        switch (lex.curToken().getType()) {
            case _END -> {
			}
            case RBRACKET -> {
			}
            case MINUS -> {
                if (!lex.curToken().getType().name().equals("MINUS")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: MINUS");
                }
                String MINUS_1 = lex.curToken().getValue();
                ePrime_0.add(new Tree("MINUS"));
                lex.nextToken();
                T t_2 = t();
                ePrime_0.add(t_2);
				{
        ePrime_0.val = acc - t_2.val;
      }
                EPrime ePrime_3 = ePrime(ePrime_0.val);
                ePrime_0.add(ePrime_3);
				{
        ePrime_0.val = ePrime_3.val;
      }
			}
            case PLUS -> {
                if (!lex.curToken().getType().name().equals("PLUS")) {
                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: PLUS");
                }
                String PLUS_1 = lex.curToken().getValue();
                ePrime_0.add(new Tree("PLUS"));
                lex.nextToken();
                T t_2 = t();
                ePrime_0.add(t_2);
				{
        ePrime_0.val = acc + t_2.val;
      }
                EPrime ePrime_3 = ePrime(ePrime_0.val);
                ePrime_0.add(ePrime_3);
				{
        ePrime_0.val = ePrime_3.val;
      }
			}
            default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
        }
        if (ePrime_0.getChildren().size() == 0) {
            ePrime_0.add(new Tree("EPS"));
            {
        System.out.println("ePrime is empty, cur acc: " + acc);
        ePrime_0.val = acc;
      }
        }
        return ePrime_0;
    }

    public E parse(String expression) {
        this.lex = new CalculatorLexicalAnalyzer(expression);
        lex.nextToken();
        return e();
    }

    public class T extends Tree {
		public int val;

        public T(String node) {
            super(node);
        }
    }

    public class E extends Tree {
		public int val;

        public E(String node) {
            super(node);
        }
    }

    public class TPrime extends Tree {
		public int val;

        public TPrime(String node) {
            super(node);
        }
    }

    public class F extends Tree {
		public int val;

        public F(String node) {
            super(node);
        }
    }

    public class EPrime extends Tree {
		public int val;

        public EPrime(String node) {
            super(node);
        }
    }

}