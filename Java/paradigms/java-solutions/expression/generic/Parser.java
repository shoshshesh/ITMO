package expression.generic;

import expression.generic.TripleExpression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<T extends Number> {
    TripleExpression<T> parse(String expression);
}
