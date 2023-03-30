package Game_4_in_the_row;

public interface Position {
    Cell getTurn();

    int getLength();

    int getHeight();

    int getLtw();

    boolean isValid(Move move);
}
