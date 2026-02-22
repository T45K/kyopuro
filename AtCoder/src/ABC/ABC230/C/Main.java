package ABC.ABC230.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/*
(i,j)が(A+k,B+k)または(A+k,B-k)という条件から
i-j = A-B
i+j = A+B
が見えてくる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        scanner.nextLong();
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();
        final long p = scanner.nextLong();
        final long q = scanner.nextLong();
        final long r = scanner.nextLong();
        final long s = scanner.nextLong();

        final String answer = LongStream.rangeClosed(p, q)
            .mapToObj(i -> LongStream.rangeClosed(r, s)
                .mapToObj(j -> (i - j == a - b || i + j == a + b) ? "#" : ".")
                .collect(Collectors.joining())
            ).collect(Collectors.joining("\n"));
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
