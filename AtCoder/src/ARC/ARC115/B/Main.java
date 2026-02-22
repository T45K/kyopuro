package ARC.ARC115.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final String YES = "Yes";
    private static final String NO = "No";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        final MinItem minItem = IntStream.range(0, n)
            .mapToObj(i -> new MinItem(i, Arrays.stream(table[i]).min().orElseThrow()))
            .min(Comparator.comparingInt(item -> item.value))
            .orElseThrow();

        final int[] a = new int[n];
        final int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = table[minItem.row][i] - minItem.value;
        }
        for (int i = 0; i < n; i++) {
            a[i] = table[i][0] - b[0];
            for (int j = 1; j < n; j++) {
                if (table[i][j] - b[j] != a[i]) {
                    System.out.println(NO);
                    return;
                }
            }
        }

        final String aAnswer = Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(" "));
        final String bAnswer = Arrays.stream(b).mapToObj(Integer::toString).collect(Collectors.joining(" "));
        System.out.println(YES);
        System.out.println(aAnswer);
        System.out.println(bAnswer);
    }

    private static class MinItem {
        final int row;
        final int value;

        MinItem(final int row, final int value) {
            this.row = row;
            this.value = value;
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
