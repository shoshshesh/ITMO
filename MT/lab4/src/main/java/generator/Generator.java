package generator;

import grammar.Grammar;

public class Generator {
    private final Grammar grammar;


    public Generator(Grammar grammar) {
        this.grammar = grammar;
    }

    public void generateAll() {
        TokenTypesGenerator tokenTypesGenerator = new TokenTypesGenerator(grammar);
        TokenGenerator tokenGenerator = new TokenGenerator(grammar);
        ParseExceptionGenerator exceptionGenerator = new ParseExceptionGenerator(grammar);
        LexicalAnalyzerGenerator lexGen = new LexicalAnalyzerGenerator(grammar);
        TreeGenerator treeGenerator = new TreeGenerator(grammar);
        ParserGenerator parserGenerator = new ParserGenerator(grammar);

        tokenTypesGenerator.generateClass();
        tokenGenerator.generateClass();
        exceptionGenerator.generateClass();
        lexGen.generateClass();
        treeGenerator.generateClass();
        parserGenerator.generateClass();
    }
}
