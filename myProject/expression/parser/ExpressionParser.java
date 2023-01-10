package expression.parser;

import expression.*;
import expression.parser.TripleParser;

public final class ExpressionParser implements TripleParser {
    public static Object parse(final StringCharSource source) {
        return new ExpressionPa(source).parsExpression();
    }

    public TripleExpression parse(final String source) {
        return (TripleExpression) parse(new StringCharSource(source));
    }


    private static class ExpressionPa extends BaseParser {
        public ExpressionPa(final CharSource source) {
            super(source);
        }


        private void skipWhitespase() {
            char space = take();
            while (Character.isWhitespace(space)) {
                space = take();
            }
            back();
        }

        private Exep parsExpression() {
            skipWhitespase();
            if (eof()) {
                throw error("Input some, pls");
            } else {
                return prior0();
            }
        }

        private Exep prior0() {
            Exep value = prior1();
            while (true) {
                if (eof()) {
                    return value;
                }
                skipWhitespase();
                char next = take();
                switch (next) {
                    case 's' -> {
                        expect("et");
                        value = new Set(value, prior1());
                    }
                    case 'c' -> {
                        expect("lear");
                        value = new Clear(value, prior1());
                    }
                    case ')' -> {
                        back();
                        return value;
                    }
                    default -> throw error("Unsupported input " + next);
                }
            }
        }

        private Exep prior1() {
            Exep value = prior2();
            while (true) {
                if (eof()) {
                    back();
                    return value;
                }
                skipWhitespase();
                char next = take();
                switch (next) {
                    case '+' -> value = new Add(value, prior2());
                    case '-' -> value = new Subtract(value, prior2());
                    case 's', 'c', ')' -> {
                        back();
                        return value;
                    }
                    default -> throw error("Unsupported input " + next);
                }
            }
        }

        private Exep prior2() {
            Exep value = prior3();
            while (true) {
                skipWhitespase();
                char next = take();
                take();
                if (eof()) {
                    return value;
                }
                back();
                switch (next) {
                    case '*' -> value = new Multiply(value, prior3());
                    case '/' -> value = new Divide(value, prior3());
                    case 's', 'c', ')', '+', '-' -> {
                        back();
                        return value;
                    }
                    default -> throw new RuntimeException("Unsupported input " + next);
                }
            }
        }

        private Exep prior3() {
            skipWhitespase();
            char next = take();
            switch (next) {
                case '(':
                    Exep value = parsExpression();
                    char ch = take();
                    if (ch != ')') {
                        throw error("Unsupported input " + ch);
                    }
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
                                return new Negate(new Variable(String.valueOf(next)));
                            }
                            back();
                            skipWhitespase();
                            next = take();
                            if (next == '-' || (next <= '9' && next >= '0' || next == 'x' || next == 'y' || next == 'z')) {
                                back();
                                return new Negate(prior3());
                            } else if (next == '(') {
                                Exep valu = parsExpression();
                                char chh = take();
                                if (chh != ')') {
                                    throw error("Unsupported input " + chh);
                                }
                                return new Negate(valu);
                            } else {
                                throw error("Unsupported input " + next);
                            }
                        }
                        while (!eof() && next <= '9' && next >= '0') {
                            sb.append(next);
                            next = take();
                        }
                        back();
                        skipWhitespase();
                        return new Const(Integer.parseInt(String.valueOf(sb)));
                    } else {
                        throw error("Unsupported input " + next);
                    }
            }
        }

    }
}
