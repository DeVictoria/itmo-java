package Game_4_in_the_row;


public class SequencePlayer implements Player {
    private final int Number;
    private int winStatus =0;

    public SequencePlayer(int Number){
        this.Number = Number;
    }

    @Override
    public Move move(Position position) {
        for(int r=0; r<position.getHeight(); r++){
            for(int c=0; c<position.getLength(); c++) {
                final Move move = new Move(r, c, position.getTurn());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("No valid moves");
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
