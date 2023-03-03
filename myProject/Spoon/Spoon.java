package Spoon;

import java.util.Arrays;

import static java.lang.Math.max;

public class Spoon {
    private int len = 0;
    private int[] cycle = new int[1000];
    private int count = 0;
    private int[] mas = new int[100];
    private int pos = 0;
    private int pos2 = 0;
    private final char[] input;

    public Spoon(String str) {
        this.input = str.toCharArray();
    }

    // :NOTE: * команды не разделены пробелами
    public void doCommand(String str) {
        if (str.equals("1")) {
            plus();
        }
        if (str.equals("000")) {
            minus();
        }
        if (str.equals("010")) {
            next();
        }
        if (str.equals("011")) {
            back();
        }
        if (str.equals("0011")) {
            end();
        }
        if (str.equals("00100")) {
            start();
        }
        if (str.equals("001010")) {
            print1();
        }
        if (str.equals("0010110")) {
            input();
        }
        if (str.equals("00101110")) {
            printAll();
        }
    }

    private void plus() {
        mas[pos]++;
    }

    private void minus() {
        mas[pos]--;
    }

    private void next() {
        pos++;
        len = max(len, pos + 1);
        if (mas.length >= pos) {
            int[] list1 = mas.clone();
            mas = Arrays.copyOf(list1, (pos * 2));
        }
    }

    private void back() {
        if (pos > 0) {
            pos--;
        } else {
            throw new IllegalArgumentException("cant do \"011\"");
        }
    }

    private void start() {
        count++;
        if (mas[pos] != 0) {
            cycle[count - 1] = pos;
        } else {
            cycle[count - 1] = -1;
        }
    }

    private void end() {
        if (cycle[count - 1] != -1) {
            pos = cycle[count - 1];
        }
    }

    private void input() {
        mas[pos] = input[pos2];
        pos2++;
    }

    private void print1() {
        System.out.println(mas[pos]);
    }

    private void printAll() {
        System.out.print("\n");
        for (int i = 0; i < len; i++) {
            System.out.print(mas[i]);
        }
        System.out.print("\n");
    }

}
