package Game_tic_tac_toe;

public interface Position {
    Cell getTurn();

    int getLength();

    int getHeight();

    int getLtw();

    boolean isValid(Move move);
}
