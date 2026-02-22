package ARC.ARC034.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final long base = (long) (Math.log10(n) + 1) * 9;
        final List<Long> answers = LongStream.rangeClosed(Math.max(0, n - base), n)
            .filter(i -> {
                final String s = Long.toString(i);
                final long sum = IntStream.range(0, s.length())
                    .map(j -> s.charAt(j) - '0')
                    .sum();
                return i + sum == n;
            })
            .boxed()
            .collect(Collectors.toList());
        System.out.println(answers.size());
        answers.forEach(System.out::println);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
