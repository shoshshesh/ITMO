package expression.generic;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Expression<T extends Number> {
    T evaluate(int x);
}


