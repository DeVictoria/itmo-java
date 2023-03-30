package Game_4_in_the_row;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.max;


public class Tournament {
    private final Player player1;

    private final Player player2;
    private final int countWin;

    private int firstMove = 1;

    Tournament(Player player1, Player player2, int countWin) {
        this.player1 = player1;
        this.player2 = player2;
        this.countWin = countWin;
    }

    private void startGame(Game game) {
        Scanner sc = new Scanner(System.in);
        TicTacToeBoard TTTBoard;
        while (true) {
            System.out.println("Input m, n, k:\n");
            try {
                int row =sc.nextInt();
                int col = sc.nextInt();
                int ltw = sc.nextInt();
                while (ltw>max(row,col)){
                    System.out.println("k must be <= m or n");
                    row =sc.nextInt();
                    col = sc.nextInt();
                    ltw = sc.nextInt();
                }
                TTTBoard = new TicTacToeBoard(row, col, ltw);
            } catch (InputMismatchException e) {
                System.out.println("\n" + sc.next() + " it's not a int number\ntry again\n");
                continue;
            }
            break;
        }
        System.out.println(game.play(TTTBoard));
    }

    public StringBuilder result() {
        while (player1.getWinStatus() < countWin && player2.getWinStatus() < countWin) {
            if (firstMove == 1) {
                final Game game = new Game(player1, player2);
                firstMove = 2;
                startGame(game);
            } else {
                final Game game = new Game(player2, player1);
                firstMove = 1;
                startGame(game);
            }
        }
        final StringBuilder sb = new StringBuilder("\n\n\n\n\nWinner is Player " +
                (player1.getWinStatus() == countWin ? 1 : 2) + "\n");
        sb.append("____________________\n");
        sb.append("| Player 1 - ").append(player1.getWinStatus()).append(" win |\n");
        sb.append("| Player 2 - ").append(player2.getWinStatus()).append(" win |\n");
        sb.append("____________________");
        return sb;
    }
}
