package parser_table;

public interface CharSource {
    char end();
    void setPos(int ind);

    boolean canBack();

    boolean hasNext();

    char next();

    char back();

    IllegalArgumentException error(String message);

}