package dz13.expression.parser;

import expression.parser.CharSource;

public class BaseParser {
    private static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;

    protected BaseParser(final CharSource source) {
        this.source = source;
    }

    protected char take() {
        ch = source.hasNext() ? source.next() : source.end();
        return ch;
    }

    protected void setPos(int ind) {
        source.setPos(ind);
        if (ind > 0) {
            ch = take();
            source.back();
        } else {
            ch = 0xffff;
        }
    }
    protected void skipWhitespase() {
        char space = take();
        while (Character.isWhitespace(space)) {
            space = take();
        }
        if(!eof()) {
            back();
        }
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        take();
        if (test(expected)) {
            return true;
        }
        if (source.canBack()) {
            back();
        }
        return false;
    }


    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return test(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void back() {
        ch = source.back();
    }

}