package Game_4_in_the_row;

public interface Player {
    Move move (Position position);

    int getWinStatus();
    void addWin();
    int getNumber();
}
