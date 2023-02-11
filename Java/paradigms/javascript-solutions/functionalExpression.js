let variable = name => (X, Y, Z) => {
    switch (name) {
        case "x": return X;
        case "y": return Y;
        case "z": return Z;
    }
}
let cnst = value => (X, Y, Z) => value;
let add = (a, b) => binary((x, y) => x + y, a, b);
let subtract = (a, b) => binary((x, y) => x - y, a, b);
let divide = (a, b) => binary((x, y) => x / y, a, b);
let multiply = (a, b) => binary((x, y) => x * y, a, b);
let negate = a => (X, Y, Z) => -a(X, Y, Z);

let binary = (f, a, b) => (X, Y, Z) => f(a(X, Y, Z), b(X, Y, Z));

let expression = add(
    subtract(
        multiply(variable("x"), variable("x")),
        multiply(cnst(2), variable("x"))
    ),
    cnst(1)
);
for (let i = 0; i <= 10; i++) {
    console.log("x = " + i + " result = " + expression(i));
}