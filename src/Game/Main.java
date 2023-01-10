package Game;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player1 = new HumanPlayer(1);
        Player player2 = new HumanPlayer(2);
        while(true) {
            try {
                System.out.println("\nenter the count win:\n");
                Tournament tournament = new Tournament(player1, player2, sc.nextInt());
                System.out.println(tournament.result());
            } catch (InputMismatchException e) {
                System.out.println("\n" + sc.next() + " it's not a int number\ntry again\n");
                continue;
            }catch (NoSuchElementException e) {
                System.out.println("Game over(");
                break;
            }

            break;
        }
    }
}