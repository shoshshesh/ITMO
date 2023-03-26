package test;
import main.Parser;
import main.ParserException;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class Tester {

    private final Parser parser = new Parser();

    @Test
    public void testArrayInNameFunc() {
        System.out.println("===== TEST ARRAY IN NAME OF FUNCTION =====");
        String function = "int fib[](int n);";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
    }

    @Test
    public void testArrayInNameVar() {
        System.out.println("===== TEST Array1 =====");
        String function = "int fib(int n[], double d[]);";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
    }

    @Test
    public void testArrayInNameType() {
        System.out.println("===== TEST Array1 =====");
        String function = "int fib(int n[], double[] d);";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
    }

    @Test
    public void testSimple() {
        System.out.println("===== TEST SIMPLE =====");
        String function = "int fib(int n);";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
    }

    @Test
    public void testWhitespaces() {
        System.out.println("===== TEST WHITESPACES =====");
        String function = "int    fib   (   int   n    )     ;     ";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));

        function = "int    fib \n  ( \r  int   n   \t )     ;     ";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
    }

    @Test
    public void testNoArgs() {
        System.out.println("===== TEST NO ARGUMENTS =====");
        String function = "int func();";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
    }

    @Test
    public void testManyArgs() {
        System.out.println("===== TEST MANY ARGUMENTS =====");
        String function = "int func(int n, long a, char* ch, float*** fl);";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
    }

    @Test
    public void testPointers() {
        System.out.println("===== TEST POINTERS =====");
        String function = "int**    ** * func(int *********** * n);";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
    }

    @Test
    public void testNumbersInName() {
        System.out.println("===== TEST NUMBERS IN NAMES =====");
        String function = "int f22ib__2323(int ____n1231244);";
        System.out.println(function);
        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));

        try {
            function = "int _2fib(int 2n);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testPointerInNames() {
        System.out.println("===== TEST POINTERS IN NAMES =====");
        try {
            String function = "int fib(int n***);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

        try {
            String function = "int fib* * (int n****);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testNoName() {
        System.out.println("===== TEST NO NAME =====");
        try {
            String function = "int fib(int);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

        try {
            String function = "float (int a);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testNoType() {
        System.out.println("===== TEST NO TYPE =====");

        try {
            String function = "fib(int n);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

        try {
            String function = "long fib(n);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testNoBrackets() {
        System.out.println("===== TEST NO BRACKETS =====");
        try {
            String function = "int fib(int n;";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

        try {
            String function = "int fib int n);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

        try {
            String function = "int fib int n;";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testNoComas() {
        System.out.println("===== TEST NO COMAS =====");
        try {
            String function = "int fib(int n double kek char ch);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testNoArgsAfterComa() {
        System.out.println("===== TEST NO ARGS AFTER COMA =====");
        try {
            String function = "int fib(int n, );";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testNoSemicolon() {
        System.out.println("===== TEST NO SEMICOLON =====");
        try {
            String function = "int fib(int n)";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testIllegalChars() {
        System.out.println("===== TEST ILLEGAL CHARS IN NAME =====");
        try {
            String function = "int fib%(int n);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));

        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

        try {
            String function = "int fib(int n^6$);";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testSymbolsAfterSemicolon() {
        System.out.println("===== TEST SYMBOLS AFTER ; =====");
        try {
            String function = "int fib(int n);   hello";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testIncorrectPositions() {
        System.out.println("===== TEST INCORRECT POSITIONS =====");
        try {
            String function = "int ; ) fib(n int ";
            System.out.println(function);
            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

//    @Test
//    public void testNamesTheSameAsTypes() {
//        System.out.println("===== TEST SAME NAMES AND TYPES =====");
//        try {
//            String function = "int float(int n);";
//            System.out.println(function);
//            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//        } catch (ParserException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            String function = "int func(int char);";
//            System.out.println(function);
//            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//        } catch (ParserException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }

//    @Test
//    public void testVoidPointer() {
//        System.out.println("===== TEST VOID =====");
//        String function = "void fib(int n);";
//        System.out.println(function);
//        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//
//        function = "void* ** * fib(int n);";
//        System.out.println(function);
//        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//
//        try {
//            function = "void* ** * fib(int g, void n);";
//            System.out.println(function);
//            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//        } catch (ParserException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//        function = "void* ** * fib(void * v,int n);";
//        System.out.println(function);
//        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//    }

//    @Test
//    public void testUnsupportedTypes() {
//        System.out.println("===== TEST UNSUPPORTED TYPES =====");
//        try {
//            String function = "integer fib(int n);";
//            System.out.println(function);
//            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//        } catch (ParserException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            String function = "int fib(Struct n);";
//            System.out.println(function);
//            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//        } catch (ParserException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    @Test
//    public void testNameDuplication() {
//        System.out.println("===== TEST NAME DUPLICATION =====");
//        String function = "int fib(int fib);";
//        System.out.println(function);
//        System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//
//        try {
//            function = "int fib(int fib, double d, void* fib);";
//            System.out.println(function);
//            System.out.println(parser.parse(new ByteArrayInputStream(function.getBytes())));
//        } catch (ParserException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}
