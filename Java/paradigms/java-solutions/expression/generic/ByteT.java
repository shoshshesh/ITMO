package expression.generic;

import expression.exceptions.DBZException;

import java.math.BigInteger;

public class ByteT implements EvaluateInterface<Byte> {
    @Override
    public Byte getTypedValue(int x) {
        return (byte) x;
    }

    @Override
    public Byte getTypedValue(String x) {
        return Byte.parseByte(x);
    }

    @Override
    public Byte add(Byte x, Byte y) {
        return (byte) (x + y);
    }

    @Override
    public Byte subtract(Byte x, Byte y) {
        return (byte) (x - y);
    }

    @Override
    public Byte multiply(Byte x, Byte y) {
        return (byte) (x * y);
    }

    @Override
    public Byte divide(Byte x, Byte y) {
        if (y == 0) {
            throw new DBZException("division by zero in " + x + " / " + y);
        }
        return (byte) (x / y);
    }

    @Override
    public Byte negate(Byte x) {
        return (byte) -x;
    }
}
