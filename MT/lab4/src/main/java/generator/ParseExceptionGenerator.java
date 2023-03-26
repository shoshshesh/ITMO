package generator;

import grammar.Grammar;

public class ParseExceptionGenerator extends AbstractGenerator {

    public ParseExceptionGenerator(Grammar grammar) {
        super(grammar, "ParseException");
    }

    @Override
    protected String generateImports() {
        return "";
    }

    @Override
    protected String generateStartOfClass() {
        return "public class ParseException extends RuntimeException {\n\n";
    }

    @Override
    protected String generateFields() {
        return "";
    }

    @Override
    protected String generateConstructors() {
        return """
                        public ParseException(String message, int pos) {
                            super(message + " at position: " + (pos - 1) + ".");
                        }
                                
                        public ParseException(String message) {
                            super(message);
                        }
                    
                    """;
    }

    @Override
    protected String generateMethods() {
        return "";
    }
}
