"use strict";

let BinaryMap = new Map();
let UnaryMap = new Map();

function BinaryAction(part1, part2, sign, f) {
    this.part1 = part1;
    this.part2 = part2;
    this.sign = sign;
    this.f = f;
}
BinaryAction.prototype = {
    evaluate: function(x, y, z) { return this.f(this.part1.evaluate(x, y, z), this.part2.evaluate(x, y, z)) },
    toString: function() { return this.part1.toString() + " " + this.part2.toString() + " " + this.sign },
    prefix: function() { return "(" + this.sign + " " + this.part1.prefix() + " " + this.part2.prefix() + ")" },
    constructor: BinaryAction
}

function UnaryAction(part, sign, f) {
    this.part = part;
    this.sign = sign;
    this.f = f;
}
UnaryAction.prototype = {
    evaluate: function(x, y, z) { return this.f(this.part.evaluate(x, y, z))},
    toString: function() { return this.part.toString() + " " + this.sign },
    prefix: function() { return "(" + this.sign + " " + this.part.prefix() + ")" },
    constructor: UnaryAction
}

function Const(value) {
    this.value = value;
}
Const.prototype = {
    evaluate: function(x, y, z) { return this.value },
    toString: function() { return String(this.value) },
    prefix: function() { return String(this.value) },
    constructor: Const
}

function Variable(name) {
    this.name = name;
}
Variable.prototype = {
    evaluate: function(x, y, z) {
        switch (this.name) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
        }
    },
    toString: function() { return this.name },
    prefix: function() { return this.name },
    constructor: Variable
}

function Add(part1, part2) {
    BinaryAction.call(this, part1, part2, "+", (a, b) => a + b);
}
Add.prototype = Object.create(BinaryAction.prototype);
Add.prototype.constructor = Add;
BinaryMap.set("+", "new Add(a, b)")

function Subtract(part1, part2) {
    BinaryAction.call(this, part1, part2, "-", (a, b) => a - b);
}
Subtract.prototype = Object.create(BinaryAction.prototype);
Subtract.prototype.constructor = Subtract;
BinaryMap.set("-", "new Subtract(a, b)")

function Multiply(part1, part2) {
    BinaryAction.call(this, part1, part2, "*", (a, b) => a * b);
}
Multiply.prototype = Object.create(BinaryAction.prototype);
Multiply.prototype.constructor = Multiply;
BinaryMap.set("*", "new Multiply(a, b)")

function Divide(part1, part2) {
    BinaryAction.call(this, part1, part2, "/", (a, b) => a / b);
}
Divide.prototype = Object.create(BinaryAction.prototype);
Divide.prototype.constructor = Divide;
BinaryMap.set("/", "new Divide(a, b)")

function Negate(part) {
    UnaryAction.call(this, part, "negate", (a) => -a);
}
Negate.prototype = Object.create(UnaryAction.prototype);
Negate.prototype.constructor = Negate;
UnaryMap.set("negate", "new Negate(a)");

function ArcTan(part) {
    UnaryAction.call(this, part, "atan", Math.atan);
}
ArcTan.prototype = Object.create(UnaryAction.prototype);
ArcTan.prototype.constructor = ArcTan;
UnaryMap.set("atan", "new ArcTan(a)");

function ArcTan2(part1, part2) {
    BinaryAction.call(this, part1, part2, "atan2", Math.atan2);
}
ArcTan2.prototype = Object.create(BinaryAction.prototype);
ArcTan2.prototype.constructor = ArcTan2;
BinaryMap.set("atan2", "new ArcTan2(a, b)")

function Sinh(part) {
    UnaryAction.call(this, part, "sinh", Math.sinh);
}
Sinh.prototype = Object.create(UnaryAction.prototype);
Sinh.prototype.constructor = Sinh;
UnaryMap.set("sinh", "new Sinh(a)");

function Cosh(part) {
    UnaryAction.call(this, part, "cosh", Math.cosh);
}
Cosh.prototype = Object.create(UnaryAction.prototype);
Cosh.prototype.constructor = Cosh;
UnaryMap.set("cosh", "new Cosh(a)");

function ParserError(message) {
    Error.call(this, message);
    this.message = message;
}
ParserError.prototype = Object.create(Error.prototype);
ParserError.prototype.name = "ParserError";
ParserError.prototype.constructor = ParserError;

let pos;

function parsePrefix(expression) {
    pos = 0;
    expression = expression.trim();
    let parsedExpression = getParsed(expression);
    if (pos !== expression.length - 1) {
        throw new ParserError("Unexpected token at position " + (pos + 1) + " in " + expression);
    }
    return parsedExpression;
}

function getParsed(expression) {
    let i = skipSpaces(expression, 0);
    if (expression[i] === "-" && isDigit(expression[i + 1])) {
        let constStr = "-" + getElement(expression, i + 1);
        pos = constStr.length - 1;
        return new Const(Number(constStr));
    } else if (isDigit(expression[i])) {
        let constStr = getElement(expression, i);
        pos = constStr.length - 1;
        return new Const(Number(constStr));
    } else if (isVariable(getElement(expression, i))) {
        let variable = getElement(expression, i);
        pos = variable.length - 1;
        return new Variable(getElement(expression, i));
    } else if (expression[i++] === "(") {
        i = skipSpaces(expression, i);
        let sign = getElement(expression, i);
        if (!UnaryMap.has(sign) && !BinaryMap.has(sign)) {
            throw new ParserError("Sign of operation expected at position " + i + " in " + expression);
        }
        i += sign.length;
        i = skipSpaces(expression, i);
        let part1 = getElement(expression, i);
        i += part1.length;
        i = skipSpaces(expression, i);
        if (expression[i] !== ")") {
            if (UnaryMap.has(sign)) {
                throw new ParserError("Too many arguments in " + expression);
            }
            let part2 = getElement(expression, i);
            i += part2.length;
            i = skipSpaces(expression,i);
            if (expression[i] !== ")") {
                throw new ParserError("Two arguments expected: " + expression);
            }
            let parsedPart1 = getParsed(part1);
            let parsedPart2 = getParsed(part2);
            pos = i;
            return getAction(sign, parsedPart1, parsedPart2);
        } else {
            if (BinaryMap.has(sign)) {
                throw new ParserError("Not enough arguments in " + expression);
            }
            let parsedPart1= getParsed(part1);
            pos = i;
            return getAction(sign, parsedPart1);
        }
    } else {
        throw new ParserError("Unexpected token at position " + i + " in " + expression);
    }
}

function getAction(sign, a, b) {
    if (BinaryMap.has(sign)) {
        return eval(BinaryMap.get(sign))
    } else if (UnaryMap.has(sign)) {
        return eval(UnaryMap.get(sign))
    } else {
        throw new ParserError("Unknown operation: " + sign);
    }
}

function getElement(expression, i) {
    let element = "";
    if (expression[i] === "(") {
        let left = 1;
        let right = 0;
        element += "(";
        i++;
        while (left !== right) {
            if (i === expression.length) {
                throw new ParserError("Some brackets are missed in " + expression);
            }
            element += expression[i];
            if (expression[i] === "(") {
                left++;
            } else if (expression[i] === ")") {
                right++;
            }
            i++;
        }
    } else if (expression[i] === "-" && isDigit(expression[i + 1])) {
        element += "-";
        i++;
        while (i < expression.length && isDigit(expression[i])) {
            element += expression[i++];
        }
        if (i < expression.length && expression[i] !== " " && expression[i] !== "(" && expression[i] !== ")") {
            throw new ParserError("Typo in const at position " + i + " in " + expression);
        }
    } else if (isDigit(expression[i])) {
        while (i < expression.length && isDigit(expression[i])) {
            element += expression[i++];
        }
        if (i < element.length && expression[i] !== " " && expression[i] !== "(" && expression[i] !== ")") {
            throw new ParserError("Typo in const аt position " + i + " in " + expression);
        }
    } else {
        while (i < expression.length && expression[i] !== " " && expression[i] !== "(" && expression[i] !== ")") {
            element += expression[i++];
        }
    }
    return element;
}

function skipSpaces(expression, i) {
    while (i < expression.length && expression[i] === " ") {
        i++;
    }
    return i;
}

function isDigit(ch) {
    return ch !== " " && 0 <= ch && ch <= 9;
}

function isVariable(ch) {
    return ch === "x" || ch === "y" || ch === "z";
}