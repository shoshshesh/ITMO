package expression.generic;

public class Negate<T extends Number> extends UnaryAction<T> implements CommonExpression<T> {

    public Negate(CommonExpression<T> value, EvaluateInterface<T> type) {
        super(value, type);
        setSign("-");
    }

    @Override
    public T evaluate(int x) {
        return getType().negate(getPart().evaluate(x));
    }

    @Override
    public T evaluate(int x, int y, int z) {
        return getType().negate(getPart().evaluate(x, y, z));
    }
}
