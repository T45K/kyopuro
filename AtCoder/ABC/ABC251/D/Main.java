package AtCoder.ABC.ABC251.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;

/*
10進数で考えれば良い
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int w = scanner.nextInt();
        final List<String> values = new ArrayList<>();
        final Consumer<Integer> appendIfInRange = v -> {
            if (v <= w) {
                values.add(v.toString());
            }
        };
        for (int i = 1; i <= 99; i++) {
            appendIfInRange.accept(i);
            appendIfInRange.accept(i * 100);
            appendIfInRange.accept(i * 100 * 100);
        }
        appendIfInRange.accept(1_000_000);
        System.out.println(values.size());
        System.out.println(String.join(" ", values));
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
