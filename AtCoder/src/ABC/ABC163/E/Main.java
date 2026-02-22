package ABC.ABC163.E;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<Child> children = IntStream.rangeClosed(1, n)
                .mapToObj(i -> new Child(scanner.nextLong(), i))
                .sorted(Comparator.comparingLong(child -> -child.value))
                .collect(Collectors.toList());

        final long[][] dpTable = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            final Child child = children.get(i - 1);
            dpTable[i][0] = dpTable[i - 1][0] + child.value * (child.position - i);
            dpTable[0][i] = dpTable[0][i - 1] + child.value * (n - i + 1 - child.position);
            for (int j = 1; j < i; j++) {
                final int diff = i - j;
                dpTable[j][diff] = Math.max(dpTable[j - 1][diff] + child.value * (child.position - j), dpTable[j][diff - 1] + child.value * (n - diff + 1 - child.position));
            }
        }

        final long answer = LongStream.range(0, n)
                .map(i -> dpTable[(int) i][n - (int) i])
                .max().orElseThrow();
        System.out.println(answer);
    }

    static class Child {
        final long value;
        final long position;

        public Child(final long value, final long position) {
            this.value = value;
            this.position = position;
        }
    }
}
