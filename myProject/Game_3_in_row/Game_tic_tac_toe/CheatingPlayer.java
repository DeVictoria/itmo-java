package Game_3_in_row.Game_tic_tac_toe;

public class CheatingPlayer implements Player {
    private final int Number;
    private int winStatus =0;

    public CheatingPlayer(int Number){
        this.Number = Number;
    }
    @Override
    public Move move(Position position) {
        final TicTacToeBoard board = (TicTacToeBoard) position;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (r + c == 0) {
                    continue;
                }
                final Move move = new Move(r, c, position.getTurn());
                if (position.isValid(move)) {
                    board.makeMove(move);
                }
            }
        }
        return new Move(0, 0, position.getTurn());
    }

    @Override
    public int getWinStatus() {
        return winStatus;
    }

    @Override
    public void addWin() {
        winStatus++;
    }

    @Override
    public int getNumber() {
        return Number;
    }
}