package parser_table;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TableParser parse = new TableParser(sc.nextLine());
        parse.parstable();
    }
}