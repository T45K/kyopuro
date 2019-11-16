package ABC145.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long[] x = new long[n];
        final long[] y = new long[n];

        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextLong();
            y[i] = scanner.nextLong();
        }

        double result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }

                final long a = (x[i] - x[j]) * (x[i] - x[j]);
                final long b = (y[i] - y[j]) * (y[i] - y[j]);
                result += Math.sqrt(a + b);
            }
        }

        System.out.println((n - 1) * result / (n * (n - 1)));
    }
}
