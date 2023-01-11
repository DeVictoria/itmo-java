package Game_tic_tac_toe;

public class ProtectedPosition implements Position {
    private Position position;

    ProtectedPosition(Position board) {
        this.position = board;
    }

    @Override
    public Cell getTurn() {
        return position.getTurn();
    }

    @Override
    public int getLength() {
        return position.getLength();
    }

    @Override
    public int getHeight() {
        return position.getHeight();
    }

    @Override
    public int getLtw() {
        return position.getLtw();
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }


    @Override
    public String toString() {
        return position.toString();
    }
}