package expression.parser;

import expression.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser implements Parser {

    public TripleExpression parse(final String expression) {
        return new ExpressionParser().parseExpression(expression);
    }

    protected TripleExpression parseExpression(String expression) {
        List<Element> elements = getElements(expression);
        ElementBuffer elementsBuffer = new ElementBuffer(elements);
        return getExpression(elementsBuffer);
    }

    protected static TripleExpression getFactor(ElementBuffer elements) {
        Element element = elements.next();
        switch (element.type) {
            case MINUS:
                if (elements.get(elements.getPos()).type == ElementsType.NUMBER) {
                    element = elements.next();
                    return new Const(Integer.parseInt("-" + element.value));
                } else {
                    TripleExpression value1 = getFactor(elements);
                    return new UnaryMinus((Expression) value1);
                }
            case VAR:
                return new Variable(element.value);
            case NUMBER:
                return new Const(Integer.parseInt(element.value));
            case LEFT_BRACKET:
                TripleExpression value2 = getExpression(elements);
                element = elements.next();
                if (element.type != ElementsType.RIGHT_BRACKET) {
                    throw new AssertionError("Unexpected token: "
                            + element.value + ", at position: " + elements.getPos());
                }
                return value2;
            default:
                throw new AssertionError("Unexpected token: "
                        + element.value + ", at position: " + elements.getPos());
        }
    }

    protected static TripleExpression multiplyDivide(ElementBuffer elements) {
        TripleExpression value = getFactor(elements);
        while (true) {
            Element element = elements.next();
            switch (element.type) {
                case MULTIPLY:
                    value = new Multiply((Expression) value, (Expression) getFactor(elements));
                    break;
                case DIVIDE:
                    value = new Divide((Expression) value, (Expression) getFactor(elements));
                    break;
                default:
                    elements.back();
                    return value;
            }
        }
    }

    protected static TripleExpression And(ElementBuffer elements) {
        TripleExpression value = AddSubtract(elements);
        while (true) {
            Element element = elements.next();
            if (element.type == ElementsType.AND) {
                value = new BitwiseAnd((Expression) value, (Expression) AddSubtract(elements));
            } else {
                elements.back();
                return value;
            }
        }
    }

    protected static TripleExpression Or(ElementBuffer elements) {
        TripleExpression value = XOR(elements);
        while (true) {
            Element element = elements.next();
            if (element.type == ElementsType.OR) {
                value = new BitwiseOr((Expression) value, (Expression) XOR(elements));
            } else {
                elements.back();
                return value;
            }
        }
    }

    protected static TripleExpression XOR(ElementBuffer elements) {
        TripleExpression value = And(elements);
        while (true) {
            Element element = elements.next();
            if (element.type == ElementsType.XOR) {
                value = new BitwiseXOR((Expression) value, (Expression) And(elements));
            } else {
                elements.back();
                return value;
            }
        }
    }

    protected static TripleExpression AddSubtract(ElementBuffer elements) {
        TripleExpression value = multiplyDivide(elements);
        while (true) {
            Element element = elements.next();
            switch (element.type) {
                case PLUS:
                    value = new Add((Expression) value, (Expression) multiplyDivide(elements));
                    break;
                case MINUS:
                    value = new Subtract((Expression) value, (Expression) multiplyDivide(elements));
                    break;
                default:
                    elements.back();
                    return value;
            }
        }
    }

    protected static TripleExpression getExpression(ElementBuffer elements) {
        Element element = elements.next();
        if (element.type == ElementsType.EOF) {
            return null;
        } else {
            elements.back();
            return Or(elements);
        }
    }

    protected List<Element> getElements(String expression) {
        ArrayList<Element> elements = new ArrayList<>();
        int pos = 0;
        while (pos < expression.length()) {
            char ch = expression.charAt(pos);
            switch (ch) {
                case '(':
                    elements.add(new Element(ElementsType.LEFT_BRACKET, "("));
                    pos++;
                    continue;
                case ')':
                    elements.add(new Element(ElementsType.RIGHT_BRACKET, ")"));
                    pos++;
                    continue;
                case '+':
                    elements.add(new Element(ElementsType.PLUS, "+"));
                    pos++;
                    continue;
                case '-':
                    elements.add(new Element(ElementsType.MINUS, "-"));
                    pos++;
                    continue;
                case '/':
                    elements.add(new Element(ElementsType.DIVIDE, "/"));
                    pos++;
                    continue;
                case '*':
                    elements.add(new Element(ElementsType.MULTIPLY, "*"));
                    pos++;
                    continue;
                case '&':
                    elements.add(new Element(ElementsType.AND, "&"));
                    pos++;
                    continue;
                case '|':
                    elements.add(new Element(ElementsType.OR, "|"));
                    pos++;
                    continue;
                case '^':
                    elements.add(new Element(ElementsType.XOR, "^"));
                    pos++;
                    continue;
                default:
                    if (Character.isLetter(ch)) {
                        if (!elements.isEmpty() && (elements.get(elements.size() - 1).type == ElementsType.VAR || elements.get(elements.size() - 1).type == ElementsType.NUMBER)) {
                            throw new AssertionError("Unexpected symbol: " + ch + ", at position: " + pos);
                        }
                        StringBuilder var = new StringBuilder();
                        do {
                            var.append(ch);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            ch = expression.charAt(pos);
                        } while (Character.isLetter(ch));
                        elements.add(new Element(ElementsType.VAR, var.toString()));
                    } else if (Character.isDigit(ch)) {
                        if (!elements.isEmpty() && (elements.get(elements.size() - 1).type == ElementsType.NUMBER || elements.get(elements.size() - 1).type == ElementsType.VAR)) {
                            throw new AssertionError("Unexpected symbol: " + ch + ", at position: " + pos);
                        }
                        StringBuilder number = new StringBuilder();
                        do {
                            number.append(ch);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            ch = expression.charAt(pos);
                        } while (Character.isDigit(ch));
                        elements.add(new Element(ElementsType.NUMBER, number.toString()));
                    } else if (ch == ' ' || ch == '\t' || ch == '\n') {
                        pos++;
                    } else {
                        throw new AssertionError("Unexpected symbol: " + ch + ", at position: " + pos);
                    }
            }
        }
        elements.add(new Element(ElementsType.EOF, ""));
        return elements;
    }
}