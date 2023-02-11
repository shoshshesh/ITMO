#include <cstdio>
#include <string>
#include <stack>
#include <fstream>

#include "LN.h"

int main(int argc, char **argv) {
    if (argc != 3) {
        printf("Wrong number of arguments\nActual: %i\nExpected: 3\nExample: lab4.exe in.txt out.txt\n", argc);
        return 1;
    }
    std::ifstream in(argv[1]);
    if (!in.is_open()) {
        printf("Cannot open the input file");
        return 1;
    }
    std::stack<LN> numbers;
    try {
        while (!in.eof()) {
            std::string inputStr;
            in >> inputStr;
            if (inputStr == "+") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                LN c = a + b;
                numbers.push(c);
            } else if (inputStr == "-") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                LN c = b - a;
                numbers.push(c);
            } else if (inputStr == "*") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                LN c = a * b;
                numbers.push(c);
            } else if (inputStr == "/") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                LN c = b / a;
                numbers.push(c);
            } else if (inputStr == "%") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                LN c = b % a;
                numbers.push(c);
            } else if (inputStr == "_") {
                LN a(numbers.top());
                numbers.pop();
                LN c = -a;
                numbers.push(c);
            } else if (inputStr == "~") {
                LN a(numbers.top());
                numbers.pop();
                LN c = ~a;
                numbers.push(c);
            } else if (inputStr == "<") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                if (b < a) {
                    numbers.push(LN(1));
                } else {
                    numbers.push(LN(0LL));
                }
            } else if (inputStr == "<=") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                if (b <= a) {
                    numbers.push(LN(1));
                } else {
                    numbers.push(LN(0LL));
                }
            } else if (inputStr == ">") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                if (b > a) {
                    numbers.push(LN(1));
                } else {
                    numbers.push(LN(0LL));
                }
            } else if (inputStr == ">=") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                if (b >= a) {
                    numbers.push(LN(1));
                } else {
                    numbers.push(LN(0LL));
                }
            } else if (inputStr == "==") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                if (b == a) {
                    numbers.push(LN(1));
                } else {
                    numbers.push(LN(0LL));
                }
            } else if (inputStr == "!=") {
                LN a(numbers.top());
                numbers.pop();
                LN b(numbers.top());
                numbers.pop();
                if (b != a) {
                    numbers.push(LN(1));
                } else {
                    numbers.push(LN(0LL));
                }
            } else {
                numbers.push(LN(inputStr));
            }
        }
    } catch (int e) {
        in.close();
        if (e == 2) {
            printf("Cannot allocate memory");
        } else if (e == 3) {
            printf("Cannot cast to long long int: LN absolute value is too big for long long int");
            return 1;
        } else if (e == 4) {
            printf("Cannot cast to long long int: LN is NaN");
            return 1;
        } else {
            printf("Something went wrong");
        }
        return 2;
    }
    in.close();
    std::ofstream out(argv[2]);
    if (!out.is_open()) {
        printf("Cannot find and create an output file");
        return 1;
    }
    while (!numbers.empty()) {
        std::string str = numbers.top().toString();
        out << str << std::endl;
        numbers.pop();
    }
    out.close();
    return 0;
}