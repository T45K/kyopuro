package AtCoder.ARC.ARC106.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
区間スケジューリング
高橋君式（右の昇順）が最適
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        if (m == 0) {
            final String answer = IntStream.range(0, n)
                .mapToObj(i -> new Range(2 * i + 1, 2 * i + 2))
                .map(Range::toString)
                .collect(Collectors.joining("\n"));
            System.out.println(answer);
            return;
        }
        if (m < 0 || m >= n - 1) {
            System.out.println(-1);
            return;
        }

        final List<Range> list = new ArrayList<>();
        for (int i = 0; i < m + 1; i++) {
            list.add(new Range(2 + 2 * i, 2 + 2 * i + 1));
        }
        list.add(new Range(1, 2 * (m + 2)));
        for (int i = 0; i < n - (m + 2); i++) {
            list.add(new Range(2 * (m + 2) + 1 + 2 * i, 2 * (m + 2) + 1 + 2 * i + 1));
        }
        final String answer = list.stream()
            .map(Range::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class Range {
        final int left;
        final int right;

        Range(final int left, final int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return left + " " + right;
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
    