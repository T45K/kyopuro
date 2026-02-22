package ABC.ABC114;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class D {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        Map<Integer, AtomicInteger> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            count(i + 1, map);
        }

        final int biggerThan3 = (int) map.entrySet().stream()
                .filter(e -> e.getValue().get() >= 2)
                .count();

        final int biggerThan5 = (int) map.entrySet().stream()
                .filter(e -> e.getValue().get() >= 4)
                .count();

        final int biggerThan15 = (int) map.entrySet().stream()
                .filter(e -> e.getValue().get() >= 14)
                .count();

        final int biggerThan25 = (int) map.entrySet().stream()
                .filter(e -> e.getValue().get() >= 24)
                .count();

        final int biggerThan75 = (int) map.entrySet().stream()
                .filter(e -> e.getValue().get() >= 74)
                .count();

        int answer = biggerThan5 * (biggerThan5 - 1) / 2 * (biggerThan3 - 2);
        answer += biggerThan15 * (biggerThan5 - 1);
        answer += biggerThan25 * (biggerThan3 - 1);
        answer += biggerThan75;

        System.out.println(answer);

    }

    private static void count(final int number, final Map<Integer, AtomicInteger> map) {
        int tmp = number;
        for (int i = 2; i <= tmp; i++) {
            if (tmp % i == 0) {
                tmp /= i;
                map.computeIfAbsent(i, v -> new AtomicInteger()).incrementAndGet();
                i--;
            }
        }
    }
}
