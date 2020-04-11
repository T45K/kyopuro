package AtCoder.AGC.AGC023.A;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long[] array = new long[n + 1];

        for (int i = 0; i < n; i++) {
            array[i + 1] = array[i] + scanner.nextInt();
        }

        final Map<Long, AtomicLong> map = new HashMap<>();

        for (final long value : array) {
            map.computeIfAbsent(value, e -> new AtomicLong())
                    .incrementAndGet();
        }

        final long answer = map.values().stream()
                .map(e -> e.get() * (e.get() - 1) / 2)
                .mapToLong(Long::longValue)
                .sum();

        System.out.println(answer);
    }
}
