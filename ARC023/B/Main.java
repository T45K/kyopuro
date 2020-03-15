package ARC023.B;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int r = scanner.nextInt();
        final int c = scanner.nextInt();
        final int d = scanner.nextInt();

        int max = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                final int value = scanner.nextInt();
                if (i + j > d) {
                    continue;
                }

                if ((d - (i + j)) % 2 == 0) {
                    max = Math.max(max, value);
                }
            }
        }

        System.out.println(max);
    }
}
