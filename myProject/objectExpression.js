"use strict"

function AbstractOper(x, y, funk, str) {
    this.x = x;
    this.y = y;
    this.funk = funk;
    this.str = str;
}

AbstractOper.prototype = {
    evaluate: function (x, y, z) {
        return this.funk(this.x.evaluate(x, y, z), this.y == undefined ? 0 : this.y.evaluate(x, y, z));
    },
    toString: function () {
        return this.x.toString() + (this.y == undefined ? "" : (" " + this.y.toString())) + " " + this.str;
    }
}

function AbstractValue(x) {
    this.x = x;
    this.NameVar = {"x": 0, "y": 1, "z": 2};
}

AbstractValue.prototype = {
    evaluate: function (x, y, z) {
        return arguments[this.NameVar[this.x]] == undefined ? this.x : arguments[this.NameVar[this.x]]
    },
    toString: function () {
        return String(this.x);
    }
}
const BuildOAbstract = function (funk, str) {
    return function (x, y) {
        return funk == undefined ? new AbstractValue(x) : new AbstractOper(x, y, funk, str);
    }
}
const Add = BuildOAbstract((a, b) => a + b, "+");
const Subtract = BuildOAbstract((a, b) => a - b, "-");
const Multiply = BuildOAbstract((a, b) => a * b, "*");
const Divide = BuildOAbstract((a, b) => a / b, "/");
const Negate = BuildOAbstract(a => -a, "negate");
const Variable = BuildOAbstract();
const Const = BuildOAbstract();

const Oper = {"+": Add, "-": Subtract, "*": Multiply, "/": Divide, "negate": Negate,};
const NameVar = {"x": 0, "y": 1, "z": 2};
const OperLength = {"+": 2, "-": 2, "*": 2, "/": 2, "negate": 1};

let parse = function (expression) {
    let stack = [];
    let tokens = expression.split(" ").filter(token => token.length > 0);
    for (const token of tokens) {
        if (token in Oper) {
            let variable = [];
            for (let i = OperLength[token]; i > 0; i--) {
                variable[i - 1] = stack.pop();
            }
            stack.push(new Oper[token](...variable));
        } else {
            stack.push(token in NameVar ? new Variable(token) : new Const(parseInt(token)));
        }
    }
    return stack.shift();
};


