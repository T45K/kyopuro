package ABC129.E;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String l = scanner.next();
        final int n = l.length();

        final long[] undetermined = new long[n];
        final long[] determined = new long[n];
        undetermined[0] = 2;
        determined[0] = 1;

        IntStream.range(1, n)
                .forEach(i -> {
                    determined[i] = determined[i - 1] * 3;
                    determined[i] %= MOD;
                    final int c = l.charAt(i) - '0';
                    if (c == 1) {
                        undetermined[i] = undetermined[i - 1] * 2;
                        undetermined[i] %= MOD;
                        determined[i] += undetermined[i - 1];
                        determined[i] %= MOD;
                    } else {
                        undetermined[i] = undetermined[i - 1];
                    }
                });

        System.out.println((undetermined[n - 1] + determined[n - 1]) % MOD);
    }
}
