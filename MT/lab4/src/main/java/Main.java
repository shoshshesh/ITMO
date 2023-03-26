import antlr_gen.GrammarOfGrammars.GrammarOfGrammarsLexer;
import antlr_gen.GrammarOfGrammars.GrammarOfGrammarsParser;
import generator.Generator;
import grammar.Grammar;
import my_gen.Calculator.CalculatorParser;
import my_gen.Signatures.SignaturesParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String grammarStr = Files.readString(Path.of("src\\main\\grammar_description\\calculator.txt"));

        CharStream charStream = CharStreams.fromString(grammarStr);

        GrammarOfGrammarsLexer lexer = new GrammarOfGrammarsLexer(charStream);
        GrammarOfGrammarsParser parser = new GrammarOfGrammarsParser(new CommonTokenStream(lexer));

        Grammar grammar = parser.grammarOfGrammars().grammar;
        Generator generator = new Generator(grammar);
        generator.generateAll();
        CalculatorParser parserCalculator = new CalculatorParser();
        CalculatorParser.E result = parserCalculator.parse("6 - 6 + sqrt abs -9");
        System.out.println(result);
        System.out.println(result.val);
    }
}