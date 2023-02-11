package expression.generic;

import expression.exceptions.DBZException;

import java.math.BigInteger;

public class BigIntegerT implements EvaluateInterface<BigInteger> {

    @Override
    public BigInteger getTypedValue(int x) {
        return BigInteger.valueOf(x);
    }

    @Override
    public BigInteger getTypedValue(String x) {
        return BigInteger.valueOf(Long.parseLong(x));
    }

    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        if (y.equals(BigInteger.ZERO)) {
            throw new DBZException("division by zero in " + x + " / " + y);
        }
        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }
}
