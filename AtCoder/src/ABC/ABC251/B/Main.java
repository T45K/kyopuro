package ABC.ABC251.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int w = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .sorted()
            .collect(Collectors.toList());

        final boolean[] isGood = new boolean[w + 1];
        final Consumer<Integer> appendIfInRange = v -> {
            if (v <= w) {
                isGood[v] = true;
            }
        };
        for (int i = 0; i < n; i++) {
            final int v1 = list.get(i);
            appendIfInRange.accept(v1);
            for (int j = i + 1; j < n; j++) {
                final int v2 = list.get(j);
                appendIfInRange.accept(v1 + v2);
                for (int k = j + 1; k < n; k++) {
                    final int v3 = list.get(k);
                    appendIfInRange.accept(v1 + v2 + v3);
                }
            }
        }
        final long answer = IntStream.rangeClosed(1, w)
            .filter(i -> isGood[i])
            .count();
        System.out.println(answer);
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
