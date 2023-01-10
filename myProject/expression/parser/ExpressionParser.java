package expression.parser;
import expression.*;
public class ExpressionParser extends BaseParser {
    private int cou =0;
    public ExpressionParser(final CharSource source) {
        super(source);
    }
    public ExpressionParser(final String source) {
        super(new StringCharSource(source));
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
            skipWhitespase();
            char next = take();
            if (eof()) {
                back();
                return value;
            }
            switch (next) {
                case 's' -> {
                    expect("et");
                    value = new Set(value, prior1());
                }
                case 'c' -> {
                    expect("lear");
                    value = new Clear(value, prior1());
                }
                case')' -> {
                    if(cou!=0) {
                        back();
                        return value;
                    }
                    else {
                        throw error("Unsupported input " + next);
                    }
                }
                default -> throw error("Unsupported input " + next);
            }
        }
    }

    private Exep prior1() {
        Exep value = prior2();
        while (true) {
            skipWhitespase();
            char next = take();
            if (eof()) {
                back();
                return value;
            }
            switch (next) {
                case '+' -> value = new Add(value, prior2());
                case '-' -> value = new Subtract(value, prior2());
                case')' -> {
                    if(cou!=0) {
                        back();
                        return value;
                    }
                    else {
                        throw error("Unsupported input " + next);
                    }
                }
                case 's', 'c' -> {
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
            if (eof()) {
                back();
                return value;
            }
            switch (next) {
                case '*' -> value = new Multiply(value, prior3());
                case '/' -> value = new Divide(value, prior3());
                case')' -> {
                    if(cou!=0) {
                        back();
                        return value;
                    }
                    else {
                        throw error("Unsupported input " + next);
                    }
                }
                case 's', 'c', '+', '-' -> {
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
                cou++;
                Exep value = parsExpression();
                char ch = take();
                if (ch != ')') {
                    throw error("Unsupported input " + ch);
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
                    return new Const(Integer.parseInt(String.valueOf(sb)));
                } else {
                    throw error("Unsupported input " + next);
                }
        }
    }
    public void toMiniString() {
        System.out.println(parsExpression().toMiniString());
    }

}
