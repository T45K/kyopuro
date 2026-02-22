package ABC.ABC299.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int answer = binarySearch(1, n, input -> {
            System.out.println("? " + input);
            return scanner.nextInt();
        });
        System.out.println(answer);
    }

    private static int binarySearch(final int begin, final int end, final UnaryOperator<Integer> fetchValue) {
        if (begin + 1 == end) {
            return begin;
        }
        final int mid = (begin + end) / 2;
        final int value = fetchValue.apply(mid);
        if (value == 0) {
            return binarySearch(mid, end, fetchValue);
        } else {
            return binarySearch(begin, mid, fetchValue);
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
