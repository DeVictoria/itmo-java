package Game_tic_tac_toe;

public interface Player {
    Move move (Position position);

    int getWinStatus();
    void addWin();
    int getNumber();
}
