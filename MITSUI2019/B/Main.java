package MITSUI2019.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        for (int i = 0; i <= n; i++) {
            if (n == (int) ((double) i * 1.08d)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(":(");
    }
}
