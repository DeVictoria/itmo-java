package Game_tic_tac_toe;

import java.util.Random;

public class RandomPlayer implements Player {
    private final int Number;
    private int winStatus =0;
    private final Random random = new Random();

    public RandomPlayer(int Number){
        this.Number = Number;
    }


    @Override
    public Move move(Position position) {
        while(true) {
            final Move move = new Move(random.nextInt(position.getHeight()), random.nextInt(position.getLength()), position.getTurn());
            if (position.isValid(move)) {
                return move;
            }
        }
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
