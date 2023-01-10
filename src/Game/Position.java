package Game;

public interface Position {
    Cell getTurn();

    int getLength();

    int getHeight();

    int getLtw();

    boolean isValid(Move move);
}
