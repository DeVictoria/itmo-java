package Game_tic_tac_toe;

public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);

}
