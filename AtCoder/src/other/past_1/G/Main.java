package other.past_1.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();

        final int[][] table = new int[n + 1][n + 1];
        int sum = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                final int value = scanner.nextInt();
                table[i][j] = value;
                table[j][i] = value;
                sum += value;
            }
        }
        int max = sum;

        final List<int[]> list = new ArrayList<>();
        {
            final boolean[] isUsed = new boolean[n + 1];
            isUsed[1] = true;
            final int[] array = new int[n];
            array[0] = 1;
            permutation(1, array, isUsed, n, list);
        }

        for (final int[] array : list) {
            max = Math.max(max, traverse(array, 0, n, sum, table));
        }

        for (final int[] array : list) {
            int tmpLeft = 0;
            int tmpRight = sum;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    tmpLeft += table[array[i]][array[j]];
                }
                for (int j = i + 1; j < n; j++) {
                    tmpRight -= table[array[i]][array[j]];
                }
                final int traverse = traverse(array, i + 1, n, tmpRight, table);
                max = Math.max(max, tmpLeft + traverse);
            }
        }
        System.out.println(max);
    }

    private static int traverse(final int[] array, final int startInclusive, final int endExclusive, final int sum, final int[][] table) {
        int tmpLeft = 0;
        int tmpRight = sum;
        int max = sum;

        for (int i = startInclusive; i < endExclusive; i++) {
            for (int j = startInclusive; j < i; j++) {
                tmpLeft += table[array[i]][array[j]];
            }
            for (int j = i + 1; j < endExclusive; j++) {
                tmpRight -= table[array[i]][array[j]];
            }
            max = Math.max(max, tmpLeft + tmpRight);
        }
        return max;
    }

    private static void permutation(final int current, final int[] array, final boolean[] isUsed, final int n, final List<int[]> list) {
        if (current == n) {
            list.add(Arrays.copyOf(array, array.length));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (isUsed[i]) {
                continue;
            }

            isUsed[i] = true;
            array[current] = (i);
            permutation(current + 1, array, isUsed, n, list);
            isUsed[i] = false;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
