package dz13.expression.parser;


import expression.Const;
import expression.Exep;
import expression.Set;
import expression.Variable;
import expression.exceptions.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringCharSource;

public final class ExpressionParser implements TripleParser {
    public static Object parse(final expression.parser.StringCharSource source) throws ExceptionExpression {
        return new ExpressionPa(source).parsExpression();
    }

    public TripleExpression parse(final String source) throws ExceptionExpression {
        return (TripleExpression) parse(new StringCharSource(source));
    }

    private static class ExpressionPa extends BaseParser {

        private int cou =0;
        public ExpressionPa(final CharSource source) {
            super(source);
        }



        private Exep parsExpression() throws ExceptionExpression {
            skipWhitespase();
            if (eof()) {
                throw new IncorrectExpression("Expression is empty");
            } else {
                return prior0();
            }
        }

        private Exep prior0() throws ExceptionExpression {
            Exep value = prior1();
            while (true) {
                skipWhitespase();
                char next = take();
                if (eof()) {
                    back();
                    return value;
                }
                switch (next) {
                    case 's' -> {
                        back();
                        back();
                        next = take();
                        if(!Character.isWhitespace(next) & next!=')'){
                            throw new IncorrectExpression("Expected whitespace " + next);
                        }
                        next=take();
                        expect("et");
                        value = new Set(value, prior1());
                    }
                    case 'c' -> {
                        back();
                        back();
                        next = take();
                        if(!Character.isWhitespace(next) & next!=')'){
                            throw new IncorrectExpression("Expected whitespace" + next);
                        }
                        next=take();
                        expect("lear");
                        value = new CheckedClear(value, prior1());
                    }
                    case')' -> {
                        if(cou!=0) {
                            back();
                            return value;
                        }
                        else {
                            throw new IncorrectExpression("Unsupported input " + next);
                        }
                    }
                    default -> throw new IncorrectExpression("Unsupported symbol " + next);
                }
            }
        }

        private Exep prior1() throws ExceptionExpression {
            Exep value = prior2();
            while (true) {
                skipWhitespase();
                char next = take();
                if (eof()) {
                    back();
                    return value;
                }
                switch (next) {
                    case '+' -> value = new CheckedAdd(value, prior2());
                    case '-' -> value = new CheckedSubtract(value, prior2());
                    case')' -> {
                        if(cou!=0) {
                            back();
                            return value;
                        }
                        else {
                            throw new IncorrectExpression("Unsupported input " + next);
                        }
                    }
                    case 's', 'c' -> {
                        back();
                        return value;
                    }
                    default -> throw new IncorrectExpression("Unsupported symbol " + next);
                }
            }
        }

        private Exep prior2() throws ExceptionExpression {
            Exep value = prior3();
            while (true) {
                skipWhitespase();
                char next = take();
                if (eof()) {
                    back();
                    return value;
                }
                switch (next) {
                    case '*' -> value = new CheckedMultiply(value, prior3());
                    case '/' -> value = new CheckedDivide(value, prior3());
                    case')' -> {
                        if(cou!=0) {
                            back();
                            return value;
                        }
                        else {
                            throw new IncorrectExpression("Unsupported input " + next);
                        }
                    }
                    case 's', 'c', '+', '-' -> {
                        back();
                        return value;
                    }
                    default -> throw new IncorrectExpression("Unsupported symbol " + next);
                }
            }
        }

        private Exep prior3() throws ExceptionExpression {
            skipWhitespase();
            char next = take();
            switch (next) {
                case '(':
                    cou++;
                    Exep value = parsExpression();
                    char ch = take();
                    if (ch != ')') {
                        throw new IncorrectExpression("Unsupported input " + ch);
                    }
                    cou--;
                    return value;
                case 'x':
                case 'y':
                case 'z':
                    skipWhitespase();
                    return new Variable(String.valueOf(next));
                default:
                    if ((next <= '9' && next >= '0') || next == '-') {
                        StringBuilder sb = new StringBuilder();
                        sb.append(next);
                        next = take();
                        if ((sb.charAt(0) == '-') && (next > '9' || next < '0')) {
                            if (next == 'x' || next == 'y' || next == 'z') {
                                return new CheckedNegate(new Variable(String.valueOf(next)));
                            }
                            back();
                            skipWhitespase();
                            next = take();
                            if (next == '-' || (next <= '9' && next >= '0' || next == 'x' || next == 'y' || next == 'z')) {
                                back();
                                return new CheckedNegate(prior3());
                            } else if (next == '(') {
                                cou++;
                                Exep valu = parsExpression();
                                char chh = take();
                                if (chh != ')') {
                                    throw new IncorrectExpression("Unsupported input " + chh);
                                }
                                cou--;
                                return new CheckedNegate(valu);
                            } else {
                                throw new IncorrectExpression("Unsupported input " + next);
                            }
                        }
                        while (!eof() && next <= '9' && next >= '0') {
                            sb.append(next);
                            next = take();
                        }
                        back();
                        return new Const(Integer.parseInt(String.valueOf(sb)));
                    } else {
                        switch (next) {
                            case 's', 'c', '+', '-', '*', '/', ')','\0' -> throw new IncorrectExpression("expected argument before " + (next=='\0'?"end":next));
                        }
                        throw new IncorrectExpression("Unsupported symbol: " + next);
                    }
            }
        }

    }
}
