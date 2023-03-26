package grammar;

import grammar.term.NonTerminal;
import grammar.term.Term;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private final NonTerminal left;
    private final List<Term> right;

    public Rule(NonTerminal left) {
        this.left = left;
        this.right = new ArrayList<>();
    }

    public Rule(NonTerminal left, List<Term> right) {
        this.left = left;
        this.right = right;
    }

    public NonTerminal getLeft() {
        return left;
    }

    public List<Term> getRight() {
        return right;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(left.getName() + " ---> ");
        for (Term term : right) {
            result.append(term.toString()).append(" ");
        }
        return result.toString();
    }
}