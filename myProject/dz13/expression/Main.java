
package dz13.expression;

import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "x * y *";
        ExpressionParser PARSER = new ExpressionParser();
        System.out.println("x       f");
        for(int i = 0; i<=10; i++) {
            try {
                System.out.println(i + "       " + PARSER.parse(s).evaluate(i, 0, 0));
            }
            catch (ExceptionExpression e){
                System.out.println(i + "       " + e.getMessage());
            }
        }

    }
}