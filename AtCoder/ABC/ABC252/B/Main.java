package AtCoder.ABC.ABC252.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Integer> a = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final Set<Integer> b = Stream.generate(scanner::nextInt)
            .limit(k)
            .collect(Collectors.toSet());

        final int max = a.stream().max(Comparator.naturalOrder()).orElseThrow();
        for (int i = 0; i < n; i++) {
            final int value = a.get(i);
            if (value < max) {
                continue;
            }
            if (b.contains(i + 1)) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
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
