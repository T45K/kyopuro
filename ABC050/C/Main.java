package ABC050.C;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final boolean answer = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.groupingBy(k -> k))
                .entrySet().stream()
                .allMatch(entry -> isPromised(n, entry));

        if (!answer) {
            System.out.println(0);
        } else {
            System.out.println(iterativePow(2, n / 2));
        }
    }

    private static boolean isPromised(final int n, final Map.Entry<Integer, List<Integer>> entry) {
        if (entry.getKey() % 2 == n % 2) {
            return false;
        }

        if (entry.getKey() == 0) {
            return entry.getValue().size() == 1;
        }

        return entry.getValue().size() == 2;
    }

    private static long iterativePow(long a, long b) {
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= a;
                tmp %= MOD;
            }
            a *= a;
            a %= MOD;
            b >>= 1;
        }

        return tmp;
    }
}
