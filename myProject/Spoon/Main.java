package Spoon;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// :NOTE: # ограничения на память
public class Main {

    // :NOTE: - Boolean?
    private static Boolean in(String[] m, String str) {
        for (int i = 0; i < m.length; i++) {
            if (m[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    // :NOTE: * Обработка ошибок
    public static void main(String[] args) throws IOException {
        Scanner sc1 = new Scanner(new File(args[0]));
        Scanner sc2 = new Scanner(new File(args[1]));
        Scanner sc = new Scanner(System.in);
        int max = 0;
        while (true) {
            try {
                System.out.println("input max count program");
                max = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\n" + sc.next() + " it's not a int number\ntry again\n");
            } catch (NoSuchElementException e) {
                System.out.println("GG");
                System.exit(0);
                break;
            }
        }
        Spoon spoon = new Spoon(sc2.nextLine());
        String command = sc1.nextLine();
        String[] m = new String[10];
        m[0] = "1";
        m[1] = "000";
        m[2] = "010";
        m[3] = "011";
        m[4] = "00100";
        m[5] = "0011";
        m[6] = "0010110";
        m[7] = "001010";
        m[8] = "00101110";
        m[9] = "00101111";
        String str = "";
        int co = 0;
        j:
        for (int i = 0; i < command.length(); i++) {
            str += command.charAt(i);
            if (in(m, str)) {
                co++;
                if (co > max) {
                    throw new IllegalArgumentException("so many command");
                }
                if (str.equals("00101111")) {
                    break j;
                } else {
                    spoon.doCommand(str);
                    str = "";
                }
            }
        }
    }
}