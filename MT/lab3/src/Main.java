import gen.PythonToCLexer;
import gen.PythonToCParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {
    public static void main(String[] args) {
        String input = """
                a = 0
                while a != 6:
                    a = a + 1
                    print(a)
                    if a == 2:
                        a = a * 2
                a = a * 2
                c = True or False
                print(5)
                if a >= 1 and 5 < 10:
                    a = a - 1
                    v = 50.121 + a * a
                elif 5 + 3 == a:
                    b = 4
                else :
                    print(555)
                    s = 55.7
                if True:
                    print(True)""";
        CharStream charStream = CharStreams.fromString(input);
        PythonToCLexer lexer = new PythonToCLexer(charStream);
        PythonToCParser parser = new PythonToCParser(new CommonTokenStream(lexer));

        System.out.println(parser.program().cProgram);
    }
}