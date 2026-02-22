package ABC.ABC137.C;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        scanner.nextLine();
        final Map<String, AtomicLong> counter = new HashMap<>();

        for (int i = 0; i < n; i++) {
            final char[] chars = scanner.nextLine().toCharArray();
            Arrays.sort(chars);
            final String string = new String(chars);

            counter.computeIfAbsent(string, e -> new AtomicLong(-1)).incrementAndGet();
        }

        long sum = 0;
        for (final AtomicLong value : counter.values()) {
            sum += getIncrementalSum(value.get());
        }

        System.out.println(sum);
    }

    private static long getIncrementalSum(final long value) {
        if (value == 0 || value == 1) {
            return value;
        }

        return (value + 1) * value / 2;
    }
}
