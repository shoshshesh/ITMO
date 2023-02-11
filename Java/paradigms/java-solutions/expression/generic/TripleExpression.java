package expression.generic;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T extends Number> {
    T evaluate(int x, int y, int z);
}
