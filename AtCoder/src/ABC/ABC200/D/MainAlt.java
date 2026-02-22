package ABC.ABC200.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainAlt {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = Math.min(scanner.nextInt(), 8); // 2^8 > 200
        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt() % 200;
        }
        final int[] sums = new int[200];
        for (final int i : IntStream.rangeClosed(1, 1 << n).toArray()) {
            final int sum = IntStream.rangeClosed(1, n)
                .filter(j -> (i & (1 << (j - 1))) > 0)
                .map(j -> array[j])
                .reduce(0, (a, b) -> (a + b) % 200);
            if (sums[sum] == 0) {
                sums[sum] = i;
                continue;
            }
            System.out.println("Yes");
            final List<String> a = decode(n, sums[sum]);
            System.out.println(a.size() + " " + String.join(" ", a));
            final List<String> b = decode(n, i);
            System.out.println(b.size() + " " + String.join(" ", b));
            return;
        }
        System.out.println("No");
    }

    private static List<String> decode(final int n, final int i) {
        return IntStream.rangeClosed(1, n)
            .filter(j -> (i & (1 << (j - 1))) > 0)
            .mapToObj(Integer::toString)
            .collect(Collectors.toList());
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
