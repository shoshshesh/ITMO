package expression.generic;

import expression.exceptions.IncorrectExpressionException;

public class Variable<T extends Number> implements CommonExpression<T> {
    private final String var;
    private final EvaluateInterface<T> type;

    public Variable(String var, EvaluateInterface<T> type) {
        this.var = var;
        this.type = type;
    }

    @Override
    public T evaluate(int x) {
        return type.getTypedValue(x);
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
        Variable<?> variable = (Variable<?>) o;
        return var.equals(variable.var);
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public T evaluate(int x, int y, int z) {
        switch (var) {
            case "x" : {
                return type.getTypedValue(x);
            }
            case "y" : {
                return type.getTypedValue(y);
            }
            case "z" : {
                return type.getTypedValue(z);
            }
        }
        throw new IncorrectExpressionException("Wrong variable's name " + var);
    }
}
