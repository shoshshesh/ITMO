package expression.generic;

public class Divide<T extends Number> extends Action<T> implements CommonExpression<T> {

    public Divide(CommonExpression<T> part1, CommonExpression<T> part2, EvaluateInterface<T> type) {
        super(part1, part2, type);
        setSign("/");
    }

    public T evaluate(int x) {
        return getType().divide(getPart1().evaluate(x), getPart2().evaluate(x));
    }

    @Override
    public T evaluate(int x, int y, int z) {
        return getType().divide(getPart1().evaluate(x, y, z), getPart2().evaluate(x, y, z));
    }
}
