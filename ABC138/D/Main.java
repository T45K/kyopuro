package ABC138.D;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();

        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int parent = scanner.nextInt() - 1;
            final int child = scanner.nextInt() - 1;

            map.put(child, parent);
        }

        final long[] answers = new long[n];

        for (int i = 0; i < q; i++) {
            final int node = scanner.nextInt() - 1;
            final int operation = scanner.nextInt();

            answers[node] += operation;
        }

        for (int i = 1; i < n; i++) {
            answers[i] += answers[map.get(i)];
        }

        for (final long answer : answers) {
            System.out.print(answer + " ");
        }
    }
}
