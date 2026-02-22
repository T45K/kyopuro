package ABC.ABC052.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final Map<Integer, Long> countMap = new HashMap<>();
        for (int i = 2; i <= n; i++) {
            count(i, countMap);
        }

        long sum = 1;
        for (final long value : countMap.values()) {
            sum = (sum * (value + 1)) % MOD;
        }

        System.out.println(sum);
    }

    private static void count(int n, final Map<Integer, Long> countMap) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                countMap.compute(i, (k, v) -> v = v == null ? 1 : v + 1);
                n /= i;
                i--;
            }
        }

        if (n != 1) {
            countMap.compute(n, (k, v) -> v = v == null ? 1 : v + 1);
        }
    }
}
