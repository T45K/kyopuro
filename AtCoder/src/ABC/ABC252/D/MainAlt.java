package ABC.ABC252.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
選択した値をjにするとき、組み合わせは(値未満の個数)*(値超過の個数)
 */
public class MainAlt {
    private static final int MAX = 2 * 100_000 + 1;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        final int[] counts = new int[MAX];
        for (final int value : list) {
            counts[value]++;
        }
        for (int i = 1; i < MAX; i++) {
            counts[i] += counts[i - 1];
        }

        final long answer = list.stream()
            .mapToLong(i -> (long) counts[i - 1] * (n - counts[i]))
            .sum();
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
