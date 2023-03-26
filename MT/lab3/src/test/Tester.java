package test;
import gen.PythonToCLexer;
import gen.PythonToCParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class Tester {

    @Test
    public void testAll() {
        String input = """
                a = int(input())
                b = float(int(float(int(input()))))
                c = a + b - a / 2 * (b - 5.78 + a)
                print(a / b)
                d = 10.20
                print(5 + (a - b) / c * (a + 41.2 - d))
                """;
        CharStream charStream = CharStreams.fromString(input);
        PythonToCLexer lexer = new PythonToCLexer(charStream);
        PythonToCParser parser = new PythonToCParser(new CommonTokenStream(lexer));
        String result = parser.program().cProgram.toString();
        System.out.println(result);
        assert result.equals("""
                #include <stdio.h>

                double d;
                double c;
                double b;
                int a;

                int main() {
                	scanf("%d", &a);
                	scanf("%lf", &b);
                	c = a + b - (double) a / 2 * (b - 5.78 + a);
                	printf("%lf\\n", (double) a / b);
                	d = 10.20;
                	printf("%lf\\n", 5 + (a - b) / c * (a + 41.2 - d));
                }""");
    }
}
