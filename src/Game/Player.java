package Game;

public interface Player {
    Move move (Position position);

    int getWinStatus();
    void addWin();
    int getNumber();
}
