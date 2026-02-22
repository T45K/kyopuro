package other.diverta2019.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

/*
余りを1から列挙していき、その値が条件に一致するかを確認していく
余りは割る数以上にならなず、商と一致する余りを求めたいことから列挙する数は√Nまでで良い
（余りが√N以上 -> 割る数が√N以上 -> 商は√N以下）
余りと商がiの時、割る数は(n-i)/i
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final long sum = LongStream.range(1, n / (long) Math.sqrt(n))
            .filter(i -> (n - i) % i == 0 && (n - i) / i > i)
            .map(i -> (n - i) / i)
            .sum();
        System.out.println(sum);
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
