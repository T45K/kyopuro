package AGC.AGC018.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        int max = scanner.nextInt();
        int gcd = max;

        for (int i = 0; i < n - 1; i++) {
            final int tmp = scanner.nextInt();
            max = Math.max(max, tmp);
            gcd = computeGCD(gcd, tmp);
        }

        System.out.println(k % gcd == 0 && k <= max ? "POSSIBLE" : "IMPOSSIBLE");
    }

    private static int computeGCD(final int a, final int b) {
        if (b > a) {
            return computeGCD(b, a);
        }

        if (b == 0) {
            return a;
        }

        return computeGCD(b, a % b);
    }
}
