package expression;

public class Variable implements Expression, TripleExpression{
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Variable variable = (Variable) o;
        return var.equals(variable.var);
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (var) {
            case "x" : {
                return x;
            }
            case "y" : {
                return y;
            }
            case "z" : {
                return z;
            }
        }
        throw new AssertionError("Wrong variables' names");
    }
}
