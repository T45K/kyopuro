package Tenka1.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long N = scanner.nextInt();
        for (long n = 1; n <= 3500; n++) {
            for (long h = 1; h <= 3500; h++) {
                final long l = 4 * h * n - N * n - N * h;
                if (l == 0) {
                    continue;
                }
                long w = N * n * h / l;
                if (w > 0 && 4 * n * h * w == N * (n * h + h * w + w * n)) {
                    System.out.println(h + " " + n + " " + w);
                    return;
                }
            }
        }
    }
}
