package my_gen.Calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {

    private final String node;
    private final List<Tree> children;

    public Tree(String node) {
        this.node = node;
        this.children = new ArrayList<>();
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void add(Tree child) {
        if (child == null) {
            return;
        }
        children.add(child);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        return this.toStringTree("", result);
    }

    private String toStringTree(String tabulation, StringBuilder result) {
        result.append(tabulation).append("---> ");
        if (this.children.size() == 0) {
            result.append("\u001B[43m").append(this.node).append("\u001B[0m").append("\n");
        } else {
            result.append(this.node).append("\n");
        }

        for (Tree child : this.children) {
            child.toStringTree(tabulation + "     |", result);
        }
        return result.toString();
    }
}