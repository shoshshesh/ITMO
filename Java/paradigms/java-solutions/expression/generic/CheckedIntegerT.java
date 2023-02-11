package expression.generic;

import expression.exceptions.DBZException;
import expression.exceptions.OverflowException;

public class CheckedIntegerT implements EvaluateInterface<Integer> {

    @Override
    public Integer getTypedValue(int x) {
        return x;
    }

    @Override
    public Integer getTypedValue(String x) {
        return Integer.parseInt(x);
    }

    @Override
    public Integer add(Integer x, Integer y) {
        if (x > 0 && y > 0 && x > Integer.MAX_VALUE - y) {
            throw new OverflowException("overflow in " + x + " + " + y);
        } else if (x < 0 && y < 0 && x < Integer.MIN_VALUE - y) {
            throw new OverflowException("overflow in " + x + " + " + y);
        }
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (x < 0 && y > 0 && x < Integer.MIN_VALUE + y) {
            throw new OverflowException("overflow in " + x + " - " + y);
        } else if (x < 0 && y < 0 && x > Integer.MAX_VALUE - y) {
            throw new OverflowException("overflow in " + x + " - " + y);
        } else if (x > 0 && y < 0 && x > Integer.MAX_VALUE + y) {
            throw new OverflowException("overflow in " + x + " - " + y);
        }
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (x > 0 && y > 0 && x > Integer.MAX_VALUE / y) {
            throw new OverflowException("overflow in " + x + " * " + y);
        } else if (x > 0 && y < -1 && x > Integer.MIN_VALUE / y) {
            throw new OverflowException("overflow in " + x + " * " + y);
        } else if (x < 0 && y > 0 && x < Integer.MIN_VALUE / y) {
            throw new OverflowException("overflow in " + x + " * " + y);
        } else if (x < 0 && y < -1 && x < Integer.MAX_VALUE / y) {
            throw new OverflowException("overflow in " + x + " * " + y);
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            throw new DBZException("division by zero in " + x + " / " + y);
        } else if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("overflow in " + x + " / " + y);
        }
        return x / y;
    }

    @Override
    public Integer negate(Integer x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("overflow in -" + x);
        }
        return -x;
    }
}
