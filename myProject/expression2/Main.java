package expression2;

import expression2.parser.ExpressionParser;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpressionParser parse = new ExpressionParser(sc.nextLine());
        parse.toMiniString();
    }
}