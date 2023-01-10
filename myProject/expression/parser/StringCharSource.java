package expression.parser;

public  class StringCharSource implements CharSource {
    private final String string;
    public int pos =0;

    public StringCharSource(String string) {
        this.string = string;
    }
    @Override
    public boolean hasNext() {
        return pos < string.length();
    }
    @Override
    public boolean canBack() {
        return pos > 0;
    }

    @Override
    public char next() {
        char result =string.charAt(pos);
        pos++;
        return result;
    }
    @Override
    public void setPos(int ind) {
        pos=ind;
    }

    @Override
    public char back() {
        pos--;
        return string.charAt(pos);
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(pos + ": "+ message);
    }
}