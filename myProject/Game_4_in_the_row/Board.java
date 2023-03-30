package Game_4_in_the_row;

public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);

}
