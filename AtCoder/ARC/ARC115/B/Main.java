package AtCoder.ARC.ARC115.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static final String YES = "Yes";
    private static final String NO = "No";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        if (n == 1) {
            System.out.println(YES);
            System.out.println(0);
            System.out.println(scanner.nextInt());
            return;
        }

        final int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = scanner.nextInt();
            }
        }
        int minRow = -1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            final int min = Arrays.stream(table[i]).min().orElseThrow();
            if (min < minValue) {
                minRow = i;
                minValue = min;
            }
        }

        final int[] a = new int[n];
        final int[] b = new int[n];
        a[minRow] = minValue;
        for (int i = 0; i < n; i++) {
            b[i] = table[minRow][i] - minValue;
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
