package grammar.term;

import grammar.Grammar;

public class NonTerminal extends Term {
    private final String heritableAttrs;
    private final String className;

    public NonTerminal(String name) {
        super(name);
        this.heritableAttrs = "";
        this.className = Grammar.makeFirstCharUpperCase(name);
    }

    public NonTerminal(String name, String heritableAttrs) {
        super(name);
        this.heritableAttrs = heritableAttrs;
        this.className = Grammar.makeFirstCharUpperCase(name);
    }

    public String getHeritableAttrs() {
        return heritableAttrs;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        String attrs = heritableAttrs.length() > 0 ? (" [" + heritableAttrs + "]") : "";
        return getName() + attrs;
    }
}
