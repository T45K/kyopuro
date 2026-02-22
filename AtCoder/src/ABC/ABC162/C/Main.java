package ABC.ABC162.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();

        long sum = 0;
        for (int a = 1; a <= k; a++) {
            for (int b = 1; b <= k; b++) {
                final long l = euclideanAlgorithm(a, b);
                for (int c = 1; c <= k; c++) {
                    sum += euclideanAlgorithm(l, c);
                }
            }
        }

        System.out.println(sum);
    }

    private static long euclideanAlgorithm(final long a, final long b) {
        if (b > a) {
            return euclideanAlgorithm(b, a);
        }

        if (b == 0) {
            return a;
        }

        return euclideanAlgorithm(b, a % b);
    }
}
