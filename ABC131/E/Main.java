package ABC131.E;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final int threshold = (n - 1) * (n - 2) / 2;

        if (k > threshold) {
            System.out.println(-1);
            return;
        }

        System.out.println(n - 1 + threshold - k);

        for (int i = 2; i <= n; i++) {
            System.out.println("1 " + i);
        }

        int counter = threshold - k;
        for (int i = 2; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (counter-- == 0) {
                    return;
                }
                System.out.println(i + " " + j);
            }
        }
    }
}
