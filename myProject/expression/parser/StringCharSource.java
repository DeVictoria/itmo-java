package expression.parser;

public  class StringCharSource implements CharSource {
    private final String string;

    private final int len;
    public int pos =0;

    public StringCharSource(String string) {
        this.string = string;
        this.len = string.length();
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
        if(pos<len) {
            char result = string.charAt(pos);
            pos++;
            return result;
        }else {
            return '@';
        }
    }
    @Override
    public char end() {
        char result ='@';
        pos++;
        return result;
    }
    @Override
    public void setPos(int ind) {
        pos=ind;
    }

    @Override
    public char back() {
        if(pos<=len) {
            pos--;
            return string.charAt(pos);
        }else {
            pos--;
            return '@';
        }
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(pos + ": "+ message);
    }
}