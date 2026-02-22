package ABC.ABC100.D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final long[][] table = new long[n][3];

        for (int i = 0; i < n; i++) {
            table[i][0] = scanner.nextLong();
            table[i][1] = scanner.nextLong();
            table[i][2] = scanner.nextLong();
        }

        long answer = calc(table, m, 1, 1, 1);
        answer = Math.max(answer, calc(table, m, 1, 1, -1));
        answer = Math.max(answer, calc(table, m, 1, -1, 1));
        answer = Math.max(answer, calc(table, m, 1, -1, -1));
        answer = Math.max(answer, calc(table, m, -1, 1, 1));
        answer = Math.max(answer, calc(table, m, -1, 1, -1));
        answer = Math.max(answer, calc(table, m, -1, -1, 1));
        answer = Math.max(answer, calc(table, m, -1, -1, -1));

        System.out.println(answer);
    }

    private static long calc(final long[][] table, final int count, final int a, final int b, final int c) {
        final List<Long> list = Arrays.stream(table)
                .map(e -> e[0] * a + e[1] * b + e[2] * c)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        return list.subList(0, count).stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}
