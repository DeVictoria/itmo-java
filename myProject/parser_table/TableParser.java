package parser_table;

public class TableParser extends BaseParser {

    private int[] values = new int[100];
    private int cou =0;

    public TableParser(final CharSource source) {
        super(source);
    }
    public TableParser(final String source) {
        super(new StringCharSource(source));
    }
    public void parstable() {
        int i =0;
        skipWhitespase();
        if(!eof()) {
            values[i]=parsExpression();
            System.out.print(values[i]);
            i++;
            while (!eof()) {
                take();
                values[i]=parsExpression();
                System.out.print(" | "+values[i]);
                i++;
                skipWhitespase();
            }
        }else {
            throw error("no input");
        }
    }

    private int parsExpression() {
        return prior1();
    }

    private int prior1() {
        int value = prior2();
        while (true) {
            skipWhitespase();
            char next = take();
            if (eof()|test('|')) {
                back();
                return value;
            }
            switch (next) {
                case '+' -> value += prior2();
                case '-' -> value -= prior2();
                case')' -> {
                    if(cou!=0) {
                        back();
                        return value;
                    } else {
                        throw error("Unsupported input " + next);
                    }
                }
                default -> throw error("WRONG INPUT");
            }
        }
    }

    private int prior2() {
        int value = prior3();
        while (true) {
            skipWhitespase();
            char next = take();
            if (eof()|test('|')) {
                back();
                return value;
            }
            switch (next) {
                case '*' -> value *= prior3();
                case '/' -> {
                    int test = prior3();
                    if(test!=0) {
                        value /= test;
                    }else {
                        throw error("can`t div by zero");
                    }
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
                case '+', '-' -> {
                    back();
                    return value;
                }
                default -> throw error("WRONG INPUT");
            }
        }
    }

    private int prior3() {
        skipWhitespase();
        char next = take();
        switch (next) {
            case '(':
                cou++;
                int value = parsExpression();
                char ch = take();
                if (ch != ')') {
                    throw error("WRONG INPUT");
                }
                cou--;
                return value;
            case '$':
                next = take();
                if ((next <= '9' && next >= '0')) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(next);
                    next = take();
                    while (next <= '9' && next >= '0') {
                        sb.append(next);
                        next = take();
                    }
                    back();
                    return values[Integer.parseInt(String.valueOf(sb))-1];
                } else {
                    throw error("WRONG INPUT");
                }
            default:
                if ((next <= '9' && next >= '0')) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(next);
                    next = take();
                    while (next <= '9' && next >= '0') {
                        sb.append(next);
                        next = take();
                    }
                    back();
                    return Integer.parseInt(String.valueOf(sb));
                } else {
                    throw error("WRONG INPUT");
                }
        }
    }

}
