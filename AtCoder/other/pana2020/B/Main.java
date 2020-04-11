package pana2020.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long h = scanner.nextInt();
        final long w = scanner.nextInt();

        if (h == 1 || w == 1) {
            System.out.println(1);
            return;
        }

        if (w % 2 == 0) {
            System.out.println(h * w / 2);
        } else if (h % 2 == 0) {
            System.out.println(h * (w / 2) + h / 2);
        } else {
            System.out.println(h * (w / 2) + h / 2 + 1);
        }
    }
}
