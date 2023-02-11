package expression.generic;

import expression.exceptions.ArithmeticExpressionException;

public class GenericTabulator implements Tabulator {
    public static void main(String[] args) {
        Object[][][] result;
        GenericTabulator gt = new GenericTabulator();
        switch (args[0]) {
            case "-i": {
                result = gt.tabulate("i", args[1], -2, 2, -2, 2, -2, 2);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            System.out.println("i = " + -2+i + " j = " + -2+j + " k = " + -2+k + " result = " + result[i][j][k]);
                        }
                    }
                }
                break;
            }
            case "-d": {
                result = gt.tabulate("d", args[1], -2, 2, -2, 2, -2, 2);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            System.out.println("i = " + -2+i + " j = " + -2+j + " k = " + -2+k + " result = " + result[i][j][k]);
                        }
                    }
                }
                break;
            }
            case "-bi": {
                result = gt.tabulate("bi", args[1], -2, 2, -2, 2, -2, 2);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            System.out.println("i = " + -2+i + " j = " + -2+j + " k = " + -2+k + " result = " + result[i][j][k]);
                        }
                    }
                }
                break;
            }
        }
    }

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] result = new Object[x2-x1+1][y2-y1+1][z2-z1+1];
        switch (mode) {
            case "i": {
                result = getResult(new CheckedIntegerT(), expression, x1, x2, y1, y2, z1, z2);
                break;
            }
            case "d": {
                result = getResult(new DoubleT(), expression, x1, x2, y1, y2, z1, z2);
                break;
            }
            case "bi": {
                result = getResult(new BigIntegerT(), expression, x1, x2, y1, y2, z1, z2);
                break;
            }
            case "u": {
                result = getResult(new IntegerT(), expression, x1, x2, y1, y2, z1, z2);
                break;
            }
            case "f": {
                result = getResult(new FloatT(), expression, x1, x2, y1, y2, z1, z2);
                break;
            }
            case "b": {
                result = getResult(new ByteT(), expression, x1, x2, y1, y2, z1, z2);
                break;
            }
        }
        return result;
    }

    private <T extends Number> Object[][][] getResult(EvaluateInterface<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        ExpressionParser<T> parser = new ExpressionParser<>(type);
        CommonExpression<T> parsedExpression = parser.parse(expression);
        Object[][][] result = new Object[x2-x1+1][y2-y1+1][z2-z1+1];
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        result[i][j][k] = parsedExpression.evaluate(x1 + i, y1 + j, z1 + k);
                    } catch (ArithmeticExpressionException e) {
                        result[i][j][k] = null;
                    }
                }
            }
        }
        return result;
    }
}
