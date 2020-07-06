package AtCoder.other.indeednow_2015_quela.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .filter(i -> i > 0)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        final int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            if (list.isEmpty()) {
                System.out.println(0);
                continue;
            }

            final int k = scanner.nextInt();
            if (k == 0) {
                System.out.println(list.get(0) + 1);
                continue;
            } else if (k >= list.size()) {
                System.out.println(0);
                continue;
            }

            final int border = list.get(k) + 1;
            System.out.println(border);
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
