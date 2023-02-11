package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        String expression = "0 - -2147483648";
        ExpressionParser parser = new ExpressionParser();
        TripleExpression test = parser.parse(expression);
        System.out.println(test.toString());
        System.out.println(test.evaluate(1,0,0));
        test = new UnaryMinus(new Multiply(new Const(5), new Subtract(new Variable("x"), new Variable("y"))));
        String strResult = test.toString();
        int result = test.evaluate(10, 6 ,9);
        System.out.println(strResult);
        System.out.println(result);

//        int result1 = new Divide(
//                new Multiply(
//                        new Variable("x"),
//                        new Const(10)
//                ),
//                new Subtract(
//                        new Add(
//                                new Const(1),
//                                new Variable("x")
//                        ),
//                        new Const(-10)
//                )
//        ).evaluate(4);
//        String strResult1 = new Divide(
//                new Multiply(
//                        new Variable("x"),
//                        new Const(10)
//                ),
//                new Subtract(
//                        new Add(
//                                new Const(1),
//                                new Variable("x")
//                        ),
//                        new Const(-10)
//                )
//        ).toString();
//        System.out.println(strResult1);
//        System.out.println(result1);
//        int result2 = new Add(
//                new Subtract(
//                        new Multiply(
//                                new Variable("x"),
//                                new Variable("y")
//                        ),
//                        new Multiply(
//                                new Const(2),
//                                new Variable("z")
//                                )
//                ),
//                new Const(1)
//        ).evaluate(1, 2, 3);
//        String strResult2 = new Add(
//                new Subtract(
//                        new Multiply(
//                                new Variable("x"),
//                                new Variable("y")
//                        ),
//                        new Multiply(
//                                new Const(2),
//                                new Variable("z")
//                        )
//                ),
//                new Const(1)
//        ).toString();
//        System.out.println(strResult2);
//        System.out.println(result2);
//        int result3 = new Add(
//                new Const(2),
//                new Variable("x")
//        ).evaluate(1);
//        String strResult3 = new Add(
//                new Const(2),
//                new Variable("x")
//        ).toString();
//        System.out.println(strResult3);
//        System.out.println(result3);
//        int result4 = new Divide(
//                new Multiply(
//                        new Variable("x"),
//                        new Variable("y")
//                ),
//                new Subtract(
//                        new Subtract(
//                                new Const(3),
//                                new Const(-4)
//                        ),
//                        new Add(
//                                new Variable("z"),
//                                new Variable("z")
//                        )
//                )
//        ).evaluate(2, 21, 7);
//        String strResult4 = new Divide(
//                new Multiply(
//                        new Variable("x"),
//                        new Variable("y")
//                ),
//                new Subtract(
//                        new Subtract(
//                                new Const(3),
//                                new Const(-4)
//                        ),
//                        new Add(
//                                new Variable("z"),
//                                new Variable("z")
//                        )
//                )
//        ).toString();
//        System.out.println(strResult4);
//        System.out.println(result4);
//        int result5 = new Divide(
//                new Multiply(
//                        new Variable("x"),
//                        new Variable("c")
//                ),
//                new Subtract(
//                        new Subtract(
//                                new Const(3),
//                                new Const(-18)
//                        ),
//                        new Add(
//                                new Variable("z"),
//                                new Variable("z")
//                        )
//                )
//        ).evaluate(2, 21, 7);
//        String strResult5 = new Divide(
//                new Multiply(
//                        new Variable("x"),
//                        new Variable("c")
//                ),
//                new Subtract(
//                        new Subtract(
//                                new Const(3),
//                                new Const(-18)
//                        ),
//                        new Add(
//                                new Variable("z"),
//                                new Variable("z")
//                        )
//                )
//        ).toString();
//        System.out.println(strResult5);
//        System.out.println(result5);
//
//        boolean res = new Multiply(new Add(new Variable("x"), new Variable("x")), new Const(2)).equals(new Multiply(new Add(new Const(1), new Variable("x")), new Const(2)));
//        System.out.println(res);
    }
}
