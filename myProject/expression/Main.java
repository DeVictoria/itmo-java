package expression;

import Game.HumanPlayer;
import Game.Player;
import Game.Tournament;
import expression.parser.ExpressionParser;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpressionParser parse = new ExpressionParser(sc.nextLine());
        parse.toMiniString();
    }
}