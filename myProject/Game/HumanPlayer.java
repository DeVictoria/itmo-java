package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final int Number;
    private int winStatus =0;

    public HumanPlayer(int Number){
        this.Number = Number;
    }
    @Override
    public Move move(Position position) {

        System.out.println("\n\nPosition: ");
        System.out.println(position);
        System.out.println("Player #"+getNumber() +", enter row and column:\n");
        while(true) {
            Scanner in = new Scanner(System.in);
            try {
                int row = in.nextInt() - 1;
                int col = in.nextInt() - 1;
                final Move move = new Move(row, col, position.getTurn());
                if (position.isValid(move)) {
                    return move;
                } else {
                    Exceptions.getException(move, position.getHeight(), position.getLength());
                }
            } catch (InputMismatchException e){
                System.out.println("\n"+in.next()+" it's not a int number\ntry again\n");
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
