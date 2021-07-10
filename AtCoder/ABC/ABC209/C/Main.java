package AtCoder.ABC.ABC209.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
ソートしても答えは変わらない
i番目の数字は(i-1)通りから選ばないといけない
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> list = Stream.generate(scanner::nextLong)
            .limit(n)
            .sorted()
            .collect(Collectors.toList());

        final boolean isOk = IntStream.range(0, n)
            .allMatch(i -> list.get(i) > i);
        if (!isOk) {
            System.out.println(0);
            return;
        }

        final long answer = IntStream.range(0, n)
            .mapToLong(i -> list.get(i) - i)
            .reduce(1, (a, b) -> a * b % MOD);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
