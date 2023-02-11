package expression.generic;

import expression.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser<T extends Number> implements Parser<T> {

    private final EvaluateInterface<T> type;

    public ExpressionParser(EvaluateInterface<T> type) {
        this.type = type;
    }

    @Override
    public CommonExpression<T> parse(final String expression) throws IncorrectExpressionException {
        return parseExpression(expression);
    }

    protected CommonExpression<T> parseExpression(String expression) {
        try {
            List<Element> elements = getElements(expression);
            ElementBuffer elementsBuffer = new ElementBuffer(elements);
            return getExpression(elementsBuffer);
        } catch (IncorrectExpressionException e) {
            System.out.println("Expression is incorrect: " + e.getMessage());
            return null;
        }
    }

    protected CommonExpression<T> getFactor(ElementBuffer elements) throws IncorrectExpressionException {
        Element element = elements.next();
        switch (element.type) {
            case MINUS:
                if (elements.get(elements.getPos()).type == ElementsType.NUMBER) {
                    element = elements.next();
                    return new Const<>(type.getTypedValue("-" + element.value), type);
                } else {
                    CommonExpression<T> value1 = getFactor(elements);
                    return new Negate<>(value1, type);

                }
            case VAR:
                return new Variable<>(element.value, type);
            case NUMBER:
                return new Const<>(type.getTypedValue(element.value), type);
            case LEFT_BRACKET:
                CommonExpression<T> value2 = getExpression(elements);
                element = elements.next();
                if (element.type != ElementsType.RIGHT_BRACKET) {
                    throw new IncorrectExpressionException("Unexpected token: "
                            + element.value + ", at position: " + elements.getPos());
                }
                return value2;
            default:
                throw new IncorrectExpressionException("Unexpected token: "
                        + element.value + ", at position: " + elements.getPos());
        }
    }

    protected CommonExpression<T> multiplyDivide(ElementBuffer elements) throws IncorrectExpressionException {
        CommonExpression<T> value = getFactor(elements);
        while (true) {
            Element element = elements.next();
            switch (element.type) {
                case MULTIPLY:
                    value = new Multiply<>(value, getFactor(elements), type);
                    break;
                case DIVIDE:
                    value = new Divide<>(value, getFactor(elements), type);
                    break;
                default:
                    elements.back();
                    return value;
            }
        }
    }

    protected CommonExpression<T> AddSubtract(ElementBuffer elements) throws IncorrectExpressionException {
        CommonExpression<T> value = multiplyDivide(elements);
        while (true) {
            Element element = elements.next();
            switch (element.type) {
                case PLUS:
                    value = new Add<>(value, multiplyDivide(elements), type);
                    break;
                case MINUS:
                    value = new Subtract<>(value, multiplyDivide(elements), type);
                    break;
                default:
                    elements.back();
                    return value;
            }
        }
    }

    protected CommonExpression<T> getExpression(ElementBuffer elements) throws IncorrectExpressionException {
        Element element = elements.next();
        if (element.type == ElementsType.EOF) {
            return null;
        } else {
            elements.back();
            return AddSubtract(elements);
        }
    }

    protected List<Element> getElements(String expression) throws IncorrectExpressionException {
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
                    default:
                        if (Character.isLetter(ch)) {
                            if (elements.size() != 0 && (elements.get(elements.size() - 1).type == ElementsType.VAR || elements.get(elements.size() - 1).type == ElementsType.NUMBER)) {
                                throw new IncorrectExpressionException("Unexpected symbol: " + ch + ", at position: " + pos);
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
                            if (elements.size() != 0 && (elements.get(elements.size() - 1).type == ElementsType.NUMBER || elements.get(elements.size() - 1).type == ElementsType.VAR)) {
                                throw new IncorrectExpressionException("Unexpected symbol: " + ch + ", at position: " + pos);
                            }
                            StringBuilder number = new StringBuilder();
                            do {
                                number.append(ch);
                                pos++;
                                if (pos >= expression.length()) {
                                    break;
                                }
                                ch = expression.charAt(pos);
                            } while (Character.isDigit(ch) || ch == '.');
                            elements.add(new Element(ElementsType.NUMBER, number.toString()));
                        } else if (ch == ' ' || ch == '\t' || ch == '\n') {
                            pos++;
                        } else {
                            throw new IncorrectExpressionException("Unexpected symbol: " + ch + ", at position: " + pos);
                        }
                }
            }
        elements.add(new Element(ElementsType.EOF, ""));
        return elements;
    }
}