package AtCoder.ABC.ABC173.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        int k = scanner.nextInt();

        final List<Long> list = IntStream.range(0, n)
            .mapToObj(i -> (long) scanner.nextInt())
            .sorted()
            .collect(Collectors.toList());

        if (k % 2 == 1 && list.get(n - 1) <= 0) {
            final long answer = (MOD - IntStream.range(0, k)
                .mapToLong(i -> list.get(n - i - 1))
                .reduce((a, b) -> Math.abs(a * b) % MOD)
                .orElseThrow()) % MOD;
            System.out.println(answer);
            return;
        }

        long production = 1;
        int left = 0;
        int right = n - 1;
        for (int i = 0; i < k; i++) {
            if (i == k - 1) {
                production *= list.get(right);
                production %= MOD;
                continue;
            }

            final long multi;
            if (list.get(left) * list.get(left + 1) > list.get(right) * list.get(right - 1)) {
                multi = list.get(left) * list.get(left + 1) % MOD;
                left += 2;
                i++;
            } else {
                multi = list.get(right);
                right--;
            }
            production *= multi;
            production %= MOD;
        }
        System.out.println((production + MOD) % MOD);
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
    